package com.example.quizapp.presentation

sealed class Screen(val route: String) {
    object WelcomeScreen: Screen("welcome_screen")
    object QuizScreen: Screen("quiz_screen")
    object ResultScreen: Screen("result_screen")
}