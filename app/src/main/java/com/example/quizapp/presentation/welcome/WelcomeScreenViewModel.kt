package com.example.quizapp.presentation.welcome

import androidx.lifecycle.ViewModel
import com.example.quizapp.utils.AppData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(): ViewModel() {

    fun updateMerchantData(name: String) {
        AppData.updateName(name)
    }
}