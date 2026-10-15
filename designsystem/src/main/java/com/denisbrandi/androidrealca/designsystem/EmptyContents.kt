package com.denisbrandi.androidrealca.designsystem

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*

@Composable
fun FullScreenMessage(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            MediumLabel(
                modifier = Modifier.padding(defaultMargin),
                text = message
            )
        }
    )
}
