package com.denisbrandi.androidrealca.onboarding.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.user.domain.model.LoginRequest
import com.denisbrandi.androidrealca.user.domain.usecase.Login
import com.denisbrandi.androidrealca.viewmodel.*
import kotlinx.coroutines.launch

internal class RealLoginViewModel(
    private val login: Login,
    private val stateDelegate: StateDelegate<LoginScreenState>,
    private val eventDelegate: EventDelegate<LoginScreenEvent>
) : LoginViewModel,
    StateViewModel<LoginScreenState> by stateDelegate,
    EventViewModel<LoginScreenEvent> by eventDelegate,
    ViewModel() {

    init {
        stateDelegate.setDefaultState(LoginScreenState(DisplayState.Form))
    }

    override fun login(email: String, password: String) {
        stateDelegate.updateState { it.copy(displayState = DisplayState.LoggingIn) }

        viewModelScope.launch {
            login(LoginRequest(email, password)).fold(
                success = {
                    eventDelegate.sendEvent(viewModelScope, LoginScreenEvent.SuccessfulLogin)
                },
                error = { loginError ->
                    stateDelegate.updateState { it.copy(displayState = DisplayState.Form) }
                    eventDelegate.sendEvent(viewModelScope, LoginScreenEvent.ShowError(loginError))
                }
            )
        }
    }
}
