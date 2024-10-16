package com.denisbrandi.androidrealca.wishlist.presentation.viewmodel

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import com.denisbrandi.androidrealca.cart.domain.usecase.AddCartItem
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
    private val addCartItem = TestAddCartItem()
    private lateinit var sut: RealWishlistViewModel
    private lateinit var stateObserver: FlowTestObserver<WishlistState>

    @Before
    fun setUp() {
        sut = RealWishlistViewModel(
            observeUserWishlist,
            removeFromWishlist,
            addCartItem,
            StateDelegate()
        )
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

    @Test
    fun `EXPECT item added to cart`() {
        sut.addProductToCart(WISHLIST_ITEM)

        assertEquals(listOf(CART_ITEM), addCartItem.invocations)
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

    private class TestAddCartItem : AddCartItem {
        val invocations = mutableListOf<CartItem>()
        override fun invoke(cartItem: CartItem) {
            invocations.add(cartItem)
        }
    }

    private companion object {
        val WISHLIST_ITEM = WishlistItem(
            id = "1",
            name = "Wireless Headphones",
            money = Money(99.99, "$"),
            imageUrl = "https://m.media-amazon.com/images/I/61fU3njgzZL._AC_SL1500_.jpg"
        )
        val WISHLIST_ITEMS = listOf(WISHLIST_ITEM)
        val CART_ITEM = CartItem(
            "1",
            "Wireless Headphones",
            Money(99.99, "$"),
            "https://m.media-amazon.com/images/I/61fU3njgzZL._AC_SL1500_.jpg",
            quantity = 1
        )
    }
}
