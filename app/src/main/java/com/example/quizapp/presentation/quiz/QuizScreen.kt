package com.example.quizapp.presentation.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.domain.model.Question
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.example.quizapp.presentation.Screen

@Composable
fun Quiz(
    navController: NavController,
    viewModel: QuizScreenViewModel = viewModel()
) {
    val questions by viewModel.questions.observeAsState(initial = emptyList())
    val currentQuestionIndex by viewModel.currentQuestionIndex.observeAsState(initial = 0)
    val isQuizFinished by viewModel.isQuizFinished.observeAsState(initial = false)
    val totalQuestions = questions.size
    val currentQuestion = questions.getOrNull(currentQuestionIndex) ?: return
    var selectedOption by remember { mutableStateOf(-1) }

    if (isQuizFinished) {
        navController.navigate(Screen.ResultScreen.route)
    } else {
        QuizQuestionScreen(
            currentQuestion = currentQuestion,
            totalQuestions = totalQuestions,
            currentQuestionIndex = currentQuestionIndex,
            selectedOption = selectedOption,
            onOptionClick = { newSelection -> selectedOption = newSelection },
            onSubmitClick = {
                viewModel.submitAnswer(selectedOption)
                selectedOption = -1
            }
        )
    }
}

@Composable
fun QuizQuestionScreen(
    currentQuestion: Question,
    totalQuestions: Int,
    currentQuestionIndex: Int,
    selectedOption: Int,
    onOptionClick: (Int) -> Unit,
    onSubmitClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentQuestion.question,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = currentQuestion.image),
            contentDescription = "Flag",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(150.dp)
                .padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinearProgressIndicator(
                progress = currentQuestionIndex.toFloat() / totalQuestions.toFloat(),
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Text(
                text = "${currentQuestionIndex + 1}/$totalQuestions",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OptionButton(
                text = currentQuestion.optionOne,
                isSelected = selectedOption == 0,
                onOptionClick = { onOptionClick(0) }
            )
            OptionButton(
                text = currentQuestion.optionTwo,
                isSelected = selectedOption == 1,
                onOptionClick = { onOptionClick(1) }
            )
            OptionButton(
                text = currentQuestion.optionThree,
                isSelected = selectedOption == 2,
                onOptionClick = { onOptionClick(2) }
            )
            OptionButton(
                text = currentQuestion.optionFour,
                isSelected = selectedOption == 3,
                onOptionClick = { onOptionClick(3) }
            )
        }

        Button(
            onClick = { onSubmitClick() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            enabled = selectedOption >= 0
        ) {
            Text(text = "Submit", color = Color.White)
        }
    }
}

@Composable
fun OptionButton(
    text: String,
    isSelected: Boolean,
    onOptionClick: () -> Unit
) {
    val borderColor = if (isSelected) Color.Blue else Color.Gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White)
            .padding(16.dp)
            .clickable { onOptionClick() }
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}
