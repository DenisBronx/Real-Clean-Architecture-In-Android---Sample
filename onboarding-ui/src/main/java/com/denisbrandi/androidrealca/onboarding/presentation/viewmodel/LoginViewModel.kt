package com.denisbrandi.androidrealca.onboarding.presentation.viewmodel

import com.denisbrandi.androidrealca.user.domain.model.LoginError
import com.denisbrandi.androidrealca.viewmodel.*

internal interface LoginViewModel : StateViewModel<LoginState>, EventViewModel<LoginViewEvent> {
    fun login(email: String, password: String)
}

data class LoginState(val contentType: ContentType)

sealed interface ContentType {
    data object Form : ContentType
    data object LoggingIn : ContentType
}

sealed interface LoginViewEvent {
    data class ShowError(val loginError: LoginError) : LoginViewEvent
    data object SuccessfulLogin : LoginViewEvent
}
