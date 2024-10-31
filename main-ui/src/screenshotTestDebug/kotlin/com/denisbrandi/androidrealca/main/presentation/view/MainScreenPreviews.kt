package com.denisbrandi.androidrealca.main.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.main.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import kotlinx.coroutines.flow.*

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
    return TestMainViewModel(MutableStateFlow(state))
}

private class TestMainViewModel(
    stateFlow: StateFlow<MainState>
) : MainViewModel,
    StateViewModel<MainState> {
    override val state = stateFlow
}
