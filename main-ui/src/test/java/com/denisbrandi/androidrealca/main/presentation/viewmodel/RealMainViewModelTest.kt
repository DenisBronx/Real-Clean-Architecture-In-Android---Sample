package com.denisbrandi.androidrealca.main.presentation.viewmodel

import com.denisbrandi.androidrealca.coroutines.testdispatcher.MainCoroutineRule
import com.denisbrandi.androidrealca.flow.testobserver.*
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.domain.usecase.ObserveUserWishlistIds
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.assertEquals

class RealMainViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val observeUserWishlistIds = TestObserveUserWishlistIds()
    private lateinit var sut: RealMainViewModel
    private lateinit var stateObserver: FlowTestObserver<MainState>

    @Before
    fun setUp() {
        sut = RealMainViewModel(observeUserWishlistIds, StateDelegate())
        stateObserver = sut.state.test()
    }

    @Test
    fun `EXPECT wishlist badge updates`() = runTest {
        observeUserWishlistIds.wishlistUpdates.emit(listOf("1", "2", "3", "4", "5"))
        observeUserWishlistIds.wishlistUpdates.emit(listOf("1", "2", "3", "4", "5", "6", "7", "8"))

        assertEquals(
            listOf(
                MainState(),
                MainState(wishlistBadge = 5),
                MainState(wishlistBadge = 8)
            ),
            stateObserver.getValues()
        )
    }

    private class TestObserveUserWishlistIds : ObserveUserWishlistIds {
        val wishlistUpdates = MutableStateFlow(emptyList<String>())
        override fun invoke(): Flow<List<String>> = wishlistUpdates
    }
}
