@file:OptIn(ExperimentalMaterial3Api::class)

package com.denisbrandi.androidrealca.designsystem

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

@Composable
fun TopBar(text: String) {
    TopAppBar(
        modifier = Modifier.shadow(elevation = topBarElevation),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Text(
                text = text,
                color = TopBarText,
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}
