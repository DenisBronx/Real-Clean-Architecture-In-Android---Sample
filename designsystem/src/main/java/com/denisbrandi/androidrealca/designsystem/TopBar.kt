@file:OptIn(ExperimentalMaterial3Api::class)

package com.denisbrandi.androidrealca.designsystem

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun TopBar(text: String) {
    TopAppBar(
        title = {
            Text(
                text = text,
                color = TopBarText,
            )
        }
    )
}
