package com.example.quizapp.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.presentation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Welcome(
    navController: NavController,
    viewModel: WelcomeScreenViewModel = hiltViewModel()
) {
    var textValue by remember { mutableStateOf("") }

    Box(
        modifier =  Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Quiz App!",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
            Card(
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF363A43)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Please enter your name.",
                        fontSize = 16.sp,
                        color = Color(0xFF7A8089)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    OutlinedTextField(
                        value = textValue,
                        onValueChange = { newValue -> textValue = newValue },
                        label = { Text("Name") },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color(0xFF363A43),
                            cursorColor = Color(0xFF363A43),
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0xFF7A8089)
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (textValue.isBlank()) {

                            } else {
                                viewModel.updateMerchantData(textValue)
                                navController.navigate(Screen.QuizScreen.route)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Text(
                            text = "Start",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}