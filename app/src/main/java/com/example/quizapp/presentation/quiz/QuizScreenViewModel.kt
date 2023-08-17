package com.example.quizapp.presentation.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.domain.interactor.QuizInteractor
import com.example.quizapp.domain.model.Question
import com.example.quizapp.utils.AppData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    quizInteractor: QuizInteractor
) : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    private var _correctAnswersCount = 0
    val correctAnswersCount: LiveData<Int> = MutableLiveData(_correctAnswersCount)

    private val _currentQuestionIndex = MutableLiveData(0)
    val currentQuestionIndex: LiveData<Int> = _currentQuestionIndex

    private val _isQuizFinished = MutableLiveData(false)
    val isQuizFinished: LiveData<Boolean> = _isQuizFinished

    init {
        _questions.value = quizInteractor.getAllItems()
    }

    fun submitAnswer(selectedOption: Int) {
        val correctOption = _questions.value?.get(_currentQuestionIndex.value ?: 0)?.correctAnswer
        if (selectedOption == correctOption) {
            _correctAnswersCount++
            (correctAnswersCount as MutableLiveData).value = _correctAnswersCount
        }

        moveToNextQuestion()
    }

    private fun moveToNextQuestion() {
        val nextQuestionIndex = (_currentQuestionIndex.value ?: 0) + 1
        if (nextQuestionIndex < (_questions.value?.size ?: 0)) {
            _currentQuestionIndex.value = nextQuestionIndex
        } else {
            AppData.updateCorrectAnswers("$_correctAnswersCount out of ${_questions.value?.size}")
            _isQuizFinished.value = true
        }
    }
}
