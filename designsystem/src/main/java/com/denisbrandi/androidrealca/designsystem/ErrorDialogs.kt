package com.denisbrandi.androidrealca.designsystem

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun CustomAlertDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    dialogText: String,
    confirmText: String
) {
    AlertDialog(
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(confirmText)
            }
        }
    )
}
