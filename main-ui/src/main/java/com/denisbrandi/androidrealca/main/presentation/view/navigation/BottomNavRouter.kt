package com.denisbrandi.androidrealca.main.presentation.view.navigation

import androidx.compose.runtime.Composable

interface BottomNavRouter {
    @Composable
    fun OpenPLPScreen()

    @Composable
    fun OpenWishlistScreen()

    @Composable
    fun OpenCartScreen()
}
