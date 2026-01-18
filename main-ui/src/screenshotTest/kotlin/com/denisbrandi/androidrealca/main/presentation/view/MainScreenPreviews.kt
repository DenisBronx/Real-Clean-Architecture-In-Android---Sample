package com.denisbrandi.androidrealca.main.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.denisbrandi.androidrealca.main.presentation.view.navigation.BottomNavRouter
import com.denisbrandi.androidrealca.main.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import kotlinx.coroutines.flow.*

@PreviewTest
@Preview
@Composable
fun PreviewNoBadgesState() {
    MainScreen(
        createViewModelWithState(MainScreenState()),
        TestBottomNavRouter()
    )
}

@PreviewTest
@Preview
@Composable
fun PreviewCartBadgeState() {
    MainScreen(
        createViewModelWithState(MainScreenState(wishlistBadge = 5, cartBadge = 9)),
        TestBottomNavRouter()
    )
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

private class TestBottomNavRouter : BottomNavRouter {
    @Composable
    override fun OpenPLPScreen() {}

    @Composable
    override fun OpenWishlistScreen() {}

    @Composable
    override fun OpenCartScreen() {}
}
