package com.example.quizapp.utils

object AppData {

    var name: String? = null
    var correctAnswers: String? = null

    fun updateName(name: String) {
        this.name = name
    }

    fun updateCorrectAnswers(correctAnswers: String) {
        this.correctAnswers = correctAnswers
    }

}