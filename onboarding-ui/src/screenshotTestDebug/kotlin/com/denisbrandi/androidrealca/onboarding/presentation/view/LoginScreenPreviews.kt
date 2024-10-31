package com.denisbrandi.androidrealca.onboarding.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.onboarding.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.*
import kotlinx.coroutines.flow.*

@Preview
@Composable
fun PreviewLoginScreenFormState() {
    LoginScreen(createViewModelWithState(LoginState(ContentType.Form))) {}
}

@Preview
@Composable
fun PreviewLoginScreenLoggingInState() {
    LoginScreen(createViewModelWithState(LoginState(ContentType.LoggingIn))) {}
}

private fun createViewModelWithState(loginState: LoginState): LoginViewModel {
    return TestLoginViewModel(MutableStateFlow(loginState), emptyFlow())
}

private class TestLoginViewModel(
    flowState: StateFlow<LoginState>,
    flowViewEvent: Flow<LoginViewEvent>
) : LoginViewModel,
    StateViewModel<LoginState>,
    EventViewModel<LoginViewEvent> {
    override val state = flowState
    override val viewEvent = flowViewEvent
    override fun login(email: String, password: String) {}
}
