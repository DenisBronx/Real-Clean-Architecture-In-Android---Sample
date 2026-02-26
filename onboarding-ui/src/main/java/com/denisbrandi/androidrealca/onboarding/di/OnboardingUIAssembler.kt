package com.denisbrandi.androidrealca.onboarding.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denisbrandi.androidrealca.onboarding.presentation.view.LoginScreen
import com.denisbrandi.androidrealca.onboarding.presentation.viewmodel.*
import com.denisbrandi.androidrealca.user.domain.usecase.Login
import com.denisbrandi.androidrealca.viewmodel.*

class OnboardingUIAssembler(
    private val login: Login
) {
    @Composable
    private fun makeLoginViewModel(): LoginViewModel {
        return viewModel {
            RealLoginViewModel(
                login,
                StateDelegate(),
                EventDelegate()
            )
        }
    }

    @Composable
    fun LoginScreenDestination(onLoggedIn: () -> Unit) {
        LoginScreen(makeLoginViewModel(), onLoggedIn)
    }
}
