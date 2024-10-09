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

@Composable
internal fun LoginScreen(loginViewModel: LoginViewModel) {
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
                Form(loginViewModel)
            }
        }
    )
}

@Composable
private fun Form(loginViewModel: LoginViewModel) {
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
    }
}
