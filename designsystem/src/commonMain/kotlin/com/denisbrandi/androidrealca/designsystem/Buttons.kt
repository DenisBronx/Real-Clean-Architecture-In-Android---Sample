package com.denisbrandi.androidrealca.designsystem

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LabelButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        content = { Text(text = text) }
    )
}

@Composable
fun LoadingButton(text: String, isLoading: Boolean, onClick: () -> Unit) {
    Button(
        enabled = !isLoading,
        content = {
            if (isLoading) {
                CircularProgressIndicator(color = Progress)
            } else {
                MediumLabel(Modifier, text = text)
            }
        },
        onClick = onClick
    )
}
