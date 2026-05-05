package com.denisbrandi.androidrealca.designsystem

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import com.denisbrandi.androidrealca.designsystem.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun RetryErrorView(
    errorMessage: String = stringResource(Res.string.generic_error_message),
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            MediumLabel(modifier = Modifier.padding(defaultMargin), text = errorMessage)
            LabelButton(text = stringResource(Res.string.retry)) { onRetry() }
        }
    )
}

@Composable
fun ErrorView(
    errorMessage: String = stringResource(Res.string.generic_error_message)
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            MediumLabel(modifier = Modifier.padding(defaultMargin), text = errorMessage)
        }
    )
}
