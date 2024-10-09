package com.denisbrandi.androidrealca.onboarding.di

import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.onboarding.presentation.view.LoginScreen
import com.denisbrandi.androidrealca.onboarding.presentation.viewmodel.*
import com.denisbrandi.androidrealca.user.domain.usecase.Login
import com.denisbrandi.androidrealca.viewmodel.*

class OnboardingUIDI(
    private val login: Login
) {
    private fun makeLoginViewModel(): LoginViewModel {
        return RealLoginViewModel(
            login,
            StateDelegate(),
            EventDelegate()
        )
    }

    @Composable
    fun LoginScreenDI() {
        LoginScreen(makeLoginViewModel())
    }
}
