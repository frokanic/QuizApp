package com.example.quizapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.presentation.quiz.Quiz
import com.example.quizapp.presentation.result.Result
import com.example.quizapp.presentation.ui.theme.QuizAppTheme
import com.example.quizapp.presentation.welcome.Welcome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.WelcomeScreen.route
                    ) {
                        composable(
                            route = Screen.WelcomeScreen.route
                        ) {
                            Welcome(navController)
                        }
                        composable(
                            route = Screen.QuizScreen.route
                        ) {
                            Quiz(navController)
                        }
                        composable(
                            route = Screen.ResultScreen.route
                        ) {
                            Result()
                        }
                    }
                }
            }
        }
    }
}
