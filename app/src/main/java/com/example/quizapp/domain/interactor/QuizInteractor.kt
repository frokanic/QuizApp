package com.example.quizapp.domain.interactor

import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository

class QuizInteractor(
    private val repository: QuizRepository
) {
    fun getAllItems(): ArrayList<Question> {
        return repository.getQuestions()
    }
}