package com.denisbrandi.androidrealca.designsystem

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MediumLabel(modifier: Modifier, text: String) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        text = text
    )
}
