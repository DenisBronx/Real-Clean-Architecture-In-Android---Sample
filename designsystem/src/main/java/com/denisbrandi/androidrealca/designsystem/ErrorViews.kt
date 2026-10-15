package com.denisbrandi.androidrealca.designsystem

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource

@Composable
inline fun RetryErrorView(
    errorMessage: String = stringResource(id = R.string.generic_error_message),
    crossinline onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            MediumLabel(modifier = Modifier.padding(defaultMargin), text = errorMessage)
            LabelButton(text = stringResource(R.string.retry)) { onRetry() }
        }
    )
}

@Composable
fun ErrorView(
    errorMessage: String = stringResource(id = R.string.generic_error_message)
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
