package com.denisbrandi.androidrealca.onboarding.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.denisbrandi.androidrealca.onboarding.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.*
import kotlinx.coroutines.flow.*

@PreviewTest
@Preview
@Composable
fun PreviewLoginScreenFormState() {
    LoginScreen(createViewModelWithState(LoginScreenState(DisplayState.Form))) {}
}

@PreviewTest
@Preview
@Composable
fun PreviewLoginScreenLoggingInState() {
    LoginScreen(createViewModelWithState(LoginScreenState(DisplayState.LoggingIn))) {}
}

private fun createViewModelWithState(loginScreenState: LoginScreenState): LoginViewModel {
    return TestLoginViewModel(MutableStateFlow(loginScreenState), emptyFlow())
}

private class TestLoginViewModel(
    flowState: StateFlow<LoginScreenState>,
    flowViewEvent: Flow<LoginScreenEvent>
) : LoginViewModel,
    StateViewModel<LoginScreenState>,
    EventViewModel<LoginScreenEvent> {
    override val state = flowState
    override val viewEvent = flowViewEvent
    override fun login(email: String, password: String) {}
}
