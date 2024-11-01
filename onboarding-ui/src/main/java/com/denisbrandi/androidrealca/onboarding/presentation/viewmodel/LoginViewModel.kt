package com.denisbrandi.androidrealca.onboarding.presentation.viewmodel

import com.denisbrandi.androidrealca.user.domain.model.LoginError
import com.denisbrandi.androidrealca.viewmodel.*

internal interface LoginViewModel : StateViewModel<LoginScreenState>, EventViewModel<LoginScreenEvent> {
    fun login(email: String, password: String)
}

internal data class LoginScreenState(val displayState: DisplayState)

internal sealed interface DisplayState {
    data object Form : DisplayState
    data object LoggingIn : DisplayState
}

internal sealed interface LoginScreenEvent {
    data class ShowError(val loginError: LoginError) : LoginScreenEvent
    data object SuccessfulLogin : LoginScreenEvent
}
