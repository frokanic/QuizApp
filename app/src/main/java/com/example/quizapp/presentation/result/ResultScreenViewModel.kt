package com.example.quizapp.presentation.result

import androidx.lifecycle.ViewModel
import com.example.quizapp.utils.AppData
import javax.inject.Inject

class ResultScreenViewModel @Inject constructor() : ViewModel() {

    fun getUsername(): String {
        return AppData.name ?: ""
    }

    fun getScore(): String? {
        return AppData.correctAnswers
    }
}