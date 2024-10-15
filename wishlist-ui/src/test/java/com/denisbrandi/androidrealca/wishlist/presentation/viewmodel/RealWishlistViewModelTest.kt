package com.denisbrandi.androidrealca.wishlist.presentation.viewmodel

import com.denisbrandi.androidrealca.coroutines.testdispatcher.MainCoroutineRule
import com.denisbrandi.androidrealca.flow.testobserver.*
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.usecase.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.assertEquals

class RealWishlistViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val observeUserWishlist = TestObserveUserWishlist()
    private val removeFromWishlist = TestRemoveFromWishlist()
    private lateinit var sut: RealWishlistViewModel
    private lateinit var stateObserver: FlowTestObserver<WishlistState>

    @Before
    fun setUp() {
        sut = RealWishlistViewModel(observeUserWishlist, removeFromWishlist, StateDelegate())
        stateObserver = sut.state.test()
    }

    @Test
    fun `EXPECT wishlist updates`() = runTest {
        observeUserWishlist.wishlistUpdates.emit(WISHLIST_ITEMS)

        assertEquals(
            listOf(
                WishlistState(emptyList()),
                WishlistState(WISHLIST_ITEMS)
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT item removed from wishlist`() = runTest {
        sut.removeItemFromWishlist("1")

        assertEquals(listOf("1"), removeFromWishlist.invocations)
    }

    private class TestObserveUserWishlist : ObserveUserWishlist {
        val wishlistUpdates = MutableStateFlow(emptyList<WishlistItem>())
        override fun invoke(): Flow<List<WishlistItem>> = wishlistUpdates
    }

    private class TestRemoveFromWishlist : RemoveFromWishlist {
        val invocations = mutableListOf<String>()
        override fun invoke(wishlistItemId: String) {
            invocations.add(wishlistItemId)
        }
    }

    private companion object {
        val WISHLIST_ITEM = WishlistItem(
            id = "1",
            name = "Wireless Headphones",
            money = Money(99.99, "$"),
            imageUrl = "https://example.com/images/wireless-headphones.jpg"
        )
        val WISHLIST_ITEMS = listOf(WISHLIST_ITEM)
    }
}
