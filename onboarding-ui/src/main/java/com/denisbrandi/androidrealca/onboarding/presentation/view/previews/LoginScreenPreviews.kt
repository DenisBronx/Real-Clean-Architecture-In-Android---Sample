package com.denisbrandi.androidrealca.onboarding.presentation.view.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.onboarding.presentation.view.LoginScreen
import com.denisbrandi.androidrealca.onboarding.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.*

@Preview
@Composable
fun PreviewLoginScreenFormState() {
    LoginScreen(createViewModelWithState(LoginState(ContentType.Form))) {}
}

@Preview
@Composable
fun PreviewLoginScreenLoggingState() {
    LoginScreen(createViewModelWithState(LoginState(ContentType.LoggingIn))) {}
}

private fun createViewModelWithState(loginState: LoginState): LoginViewModel {
    val stateDelegate = StateDelegate<LoginState>()
    stateDelegate.setDefaultState(loginState)
    return TestLoginViewModel(stateDelegate)
}

private class TestLoginViewModel(
    stateDelegate: StateDelegate<LoginState>,
    eventDelegate: EventDelegate<LoginViewEvent> = EventDelegate()
) : LoginViewModel,
    StateViewModel<LoginState> by stateDelegate,
    EventViewModel<LoginViewEvent> by eventDelegate {
    override fun login(email: String, password: String) {}
}
