package com.denisbrandi.androidrealca.main.presentation.viewmodel

import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.domain.usecase.ObserveUserCart
import com.denisbrandi.androidrealca.coroutines.testdispatcher.MainCoroutineRule
import com.denisbrandi.androidrealca.flow.testobserver.*
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.domain.usecase.ObserveUserWishlistIds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.assertEquals

class RealMainViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val observeUserWishlistIds = TestObserveUserWishlistIds()
    private val observeUserCart = TestObserveUserCart()
    private lateinit var sut: RealMainViewModel
    private lateinit var stateObserver: FlowTestObserver<MainScreenState>

    @Before
    fun setUp() {
        sut = RealMainViewModel(observeUserWishlistIds, observeUserCart, StateDelegate())
        stateObserver = sut.state.test()
    }

    @Test
    fun `EXPECT wishlist badge updates`() = runTest {
        observeUserWishlistIds.wishlistUpdates.emit(listOf("1", "2", "3", "4", "5"))
        observeUserWishlistIds.wishlistUpdates.emit(listOf("1", "2", "3", "4", "5", "6", "7", "8"))

        assertEquals(
            listOf(
                MainScreenState(),
                MainScreenState(wishlistBadge = 5),
                MainScreenState(wishlistBadge = 8)
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT cart badge updates`() = runTest {
        observeUserCart.cartUpdates.emit(Cart(listOf(makeCartItem(quantity = 5))))
        observeUserCart.cartUpdates.emit(
            Cart(
                listOf(
                    makeCartItem(quantity = 5),
                    makeCartItem(quantity = 3)
                )
            )
        )

        assertEquals(
            listOf(
                MainScreenState(),
                MainScreenState(cartBadge = 5),
                MainScreenState(cartBadge = 8)
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT both badges updates`() = runTest {
        observeUserWishlistIds.wishlistUpdates.emit(listOf("1", "2", "3", "4", "5"))
        observeUserCart.cartUpdates.emit(Cart(listOf(makeCartItem(quantity = 5))))
        observeUserWishlistIds.wishlistUpdates.emit(listOf("1", "2", "3", "4", "5", "6"))

        assertEquals(
            listOf(
                MainScreenState(),
                MainScreenState(wishlistBadge = 5),
                MainScreenState(wishlistBadge = 5, cartBadge = 5),
                MainScreenState(wishlistBadge = 6, cartBadge = 5)
            ),
            stateObserver.getValues()
        )
    }

    private class TestObserveUserWishlistIds : ObserveUserWishlistIds {
        val wishlistUpdates = MutableStateFlow(emptyList<String>())
        override fun invoke() = wishlistUpdates
    }

    private class TestObserveUserCart : ObserveUserCart {
        val cartUpdates = MutableStateFlow(Cart(emptyList()))
        override fun invoke() = cartUpdates
    }

    private fun makeCartItem(
        quantity: Int = 1
    ): CartItem {
        return CartItem(
            id = "1",
            name = "Wireless Headphones",
            money = Money(99.99, "$"),
            imageUrl = "https://example.com/images/wireless-headphones.jpg",
            quantity = quantity
        )
    }
}
