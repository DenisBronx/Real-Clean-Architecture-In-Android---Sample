package com.denisbrandi.androidrealca.cart.presentation.viewmodel

import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.domain.usecase.*
import com.denisbrandi.androidrealca.coroutines.testdispatcher.MainCoroutineRule
import com.denisbrandi.androidrealca.flow.testobserver.*
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.assertEquals

class RealCartViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val observeUserCart = TestObserveUserCart()
    private val updateCartItem = TestUpdateCartItem()
    private lateinit var sut: RealCartViewModel
    private lateinit var stateObserver: FlowTestObserver<CartState>

    @Before
    fun setUp() {
        sut = RealCartViewModel(observeUserCart, updateCartItem, StateDelegate())
        stateObserver = sut.state.test()
    }

    @Test
    fun `EXPECT cart updates`() = runTest {
        observeUserCart.cartUpdates.emit(CART)

        assertEquals(
            listOf(
                CartState(Cart(emptyList())),
                CartState(CART)
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT cart updated`() {
        sut.updateCartItemQuantity(CART_ITEM)

        assertEquals(listOf(CART_ITEM), updateCartItem.invocations)
    }

    private class TestObserveUserCart : ObserveUserCart {
        val cartUpdates = MutableStateFlow(Cart(emptyList()))
        override fun invoke(): Flow<Cart> = cartUpdates
    }

    private class TestUpdateCartItem : UpdateCartItem {
        val invocations = mutableListOf<CartItem>()
        override fun invoke(cartItem: CartItem) {
            invocations.add(cartItem)
        }
    }

    private companion object {
        val CART_ITEM = CartItem(
            "1",
            "Wireless Headphones",
            Money(99.99, "$"),
            "https://m.media-amazon.com/images/I/61fU3njgzZL._AC_SL1500_.jpg",
            quantity = 1
        )
        val CART = Cart(listOf(CART_ITEM))
    }
}
