package com.example.quizapp.data.repository

import com.example.quizapp.data.source.QuizQuestions
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository

class QuizRepositoryImpl : QuizRepository {

    override fun getQuestions(): ArrayList<Question> {
        return QuizQuestions.questions()
    }

}