package com.denisbrandi.androidrealca.onboarding.presentation.viewmodel

import com.denisbrandi.androidrealca.user.domain.model.LoginError
import com.denisbrandi.androidrealca.viewmodel.*

internal interface LoginViewModel : StateViewModel<LoginState>, EventViewModel<LoginViewEvent> {
    fun login(email: String, password: String)
}

internal data class LoginState(val contentType: ContentType)

internal sealed interface ContentType {
    data object Form : ContentType
    data object LoggingIn : ContentType
}

internal sealed interface LoginViewEvent {
    data class ShowError(val loginError: LoginError) : LoginViewEvent
    data object SuccessfulLogin : LoginViewEvent
}
