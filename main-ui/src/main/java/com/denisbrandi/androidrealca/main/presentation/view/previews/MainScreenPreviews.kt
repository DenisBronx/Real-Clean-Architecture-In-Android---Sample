package com.denisbrandi.androidrealca.main.presentation.view.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.main.presentation.view.MainScreen
import com.denisbrandi.androidrealca.main.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.*

@Preview
@Composable
fun PreviewNoBadgesState() {
    MainScreen(createViewModelWithState(MainState()), {}, {}, {})
}

@Preview
@Composable
fun PreviewCartBadgeState() {
    MainScreen(createViewModelWithState(MainState(wishlistBadge = 5, cartBadge = 9)), {}, {}, {})
}

private fun createViewModelWithState(state: MainState): MainViewModel {
    val stateDelegate = StateDelegate<MainState>()
    stateDelegate.setDefaultState(state)
    return TestMainViewModel(stateDelegate)
}

private class TestMainViewModel(
    stateDelegate: StateDelegate<MainState>
) : MainViewModel,
    StateViewModel<MainState> by stateDelegate
