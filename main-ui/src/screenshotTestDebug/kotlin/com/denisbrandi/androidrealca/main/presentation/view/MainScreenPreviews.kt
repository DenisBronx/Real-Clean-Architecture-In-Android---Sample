package com.denisbrandi.androidrealca.main.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.main.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import kotlinx.coroutines.flow.*

@Preview
@Composable
fun PreviewNoBadgesState() {
    MainScreen(createViewModelWithState(MainScreenState()), {}, {}, {})
}

@Preview
@Composable
fun PreviewCartBadgeState() {
    MainScreen(createViewModelWithState(MainScreenState(wishlistBadge = 5, cartBadge = 9)), {}, {}, {})
}

private fun createViewModelWithState(state: MainScreenState): MainViewModel {
    return TestMainViewModel(MutableStateFlow(state))
}

private class TestMainViewModel(
    stateFlow: StateFlow<MainScreenState>
) : MainViewModel,
    StateViewModel<MainScreenState> {
    override val state = stateFlow
}
