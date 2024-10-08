package com.denisbrandi.androidrealca.onboarding.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.user.domain.model.LoginRequest
import com.denisbrandi.androidrealca.user.domain.usecase.Login
import com.denisbrandi.androidrealca.viewmodel.*
import kotlinx.coroutines.launch

internal class RealLoginViewModel(
    private val login: Login,
    private val stateDelegate: StateDelegate<LoginState>,
    private val eventDelegate: EventDelegate<LoginViewEvent>
) : LoginViewModel,
    StateViewModel<LoginState> by stateDelegate,
    EventViewModel<LoginViewEvent> by eventDelegate,
    ViewModel() {

    init {
        stateDelegate.setDefaultState(LoginState(ContentType.Form))
    }

    override fun login(email: String, password: String) {
        stateDelegate.updateState { it.copy(contentType = ContentType.LoggingIn) }

        viewModelScope.launch {
            login(LoginRequest(email, password)).fold(
                success = {
                    eventDelegate.sendEvent(viewModelScope, LoginViewEvent.SuccessfulLogin)
                },
                error = { loginError ->
                    stateDelegate.updateState { it.copy(contentType = ContentType.Form) }
                    eventDelegate.sendEvent(viewModelScope, LoginViewEvent.ShowError(loginError))
                }
            )
        }
    }
}
