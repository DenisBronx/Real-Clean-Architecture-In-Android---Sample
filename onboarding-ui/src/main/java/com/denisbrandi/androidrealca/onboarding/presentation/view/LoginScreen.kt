@file:OptIn(ExperimentalMaterial3Api::class)

package com.denisbrandi.androidrealca.onboarding.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import com.denisbrandi.androidrealca.designsystem.*
import com.denisbrandi.androidrealca.onboarding.presentation.viewmodel.*
import com.denisbrandi.androidrealca.onboarding.ui.R
import com.denisbrandi.androidrealca.user.domain.model.LoginError
import kotlinx.coroutines.flow.*

@Composable
internal fun LoginScreen(
    loginViewModel: LoginViewModel,
    onLoggedIn: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.login_screen_title),
                        color = TopBarText,
                    )
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
            ) {
                Form(loginViewModel, onLoggedIn)
            }
        }
    )
}

@Composable
private fun Form(loginViewModel: LoginViewModel, onLoggedIn: () -> Unit) {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    val loginState = loginViewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.padding(bottom = defaultMargin),
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                Text(
                    modifier = Modifier.padding(noMargin),
                    text = stringResource(id = R.string.enter_email),
                    color = TextField
                )
            },
            value = emailText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                emailText = it
            }
        )
        TextField(
            modifier = Modifier.padding(bottom = defaultMargin),
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                Text(
                    modifier = Modifier.padding(noMargin),
                    text = stringResource(id = R.string.enter_password),
                    color = TextField
                )
            },
            value = passwordText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                passwordText = it
            }
        )
        val isLoading = loginState.value.contentType is ContentType.LoggingIn
        LoadingButton(text = stringResource(R.string.login_button_text), isLoading) {
            loginViewModel.login(emailText, passwordText)
        }
        LoginEvent(
            loginViewModel = loginViewModel,
            onRetryLogin = {
                loginViewModel.login(emailText, passwordText)
            },
            onLoggedIn = onLoggedIn
        )
    }
}

@Composable
private fun LoginEvent(
    loginViewModel: LoginViewModel,
    onRetryLogin: () -> Unit,
    onLoggedIn: () -> Unit
) {
    val noModalEvent = ModalEvent<LoginError>(null)
    var showErrorDialog by remember { mutableStateOf(noModalEvent) }

    LaunchedEffect(key1 = Unit) {
        loginViewModel.viewEvent.onEach { viewEvent ->
            when (viewEvent) {
                is LoginViewEvent.ShowError -> {
                    showErrorDialog = ModalEvent(viewEvent.loginError)
                }

                is LoginViewEvent.SuccessfulLogin -> {
                    onLoggedIn()
                }
            }
        }.launchIn(this)
    }

    when (showErrorDialog.viewEvent) {
        is LoginError.GenericError -> {
            CustomAlertDialog(
                onConfirm = {
                    showErrorDialog = noModalEvent
                    onRetryLogin()
                },
                onDismiss = { showErrorDialog = noModalEvent },
                dialogText = stringResource(com.denisbrandi.androidrealca.designsystem.R.string.something_went_wrong),
                confirmText = stringResource(com.denisbrandi.androidrealca.designsystem.R.string.retry)
            )
        }

        is LoginError.InvalidEmail -> {
            CustomAlertDialog(
                onConfirm = { showErrorDialog = noModalEvent },
                onDismiss = { showErrorDialog = noModalEvent },
                dialogText = stringResource(R.string.error_invalid_email),
                confirmText = stringResource(com.denisbrandi.androidrealca.designsystem.R.string.ok)
            )
        }

        is LoginError.InvalidPassword -> {
            CustomAlertDialog(
                onConfirm = { showErrorDialog = noModalEvent },
                onDismiss = { showErrorDialog = noModalEvent },
                dialogText = stringResource(R.string.error_invalid_password),
                confirmText = stringResource(com.denisbrandi.androidrealca.designsystem.R.string.ok)
            )
        }

        is LoginError.IncorrectCredentials -> {
            CustomAlertDialog(
                onConfirm = { showErrorDialog = noModalEvent },
                onDismiss = { showErrorDialog = noModalEvent },
                dialogText = stringResource(R.string.error_invalid_password),
                confirmText = stringResource(com.denisbrandi.androidrealca.designsystem.R.string.ok)
            )
        }

        else -> {}
    }
}
