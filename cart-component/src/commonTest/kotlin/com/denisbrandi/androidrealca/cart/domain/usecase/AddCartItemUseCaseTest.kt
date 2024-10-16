package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.domain.repository.TestCartRepository
import com.denisbrandi.androidrealca.user.domain.model.User
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import kotlin.test.*

class AddCartItemUseCaseTest {

    private val getUser = GetUser { USER }
    private val cartRepository = TestCartRepository()
    private val updateCartItem = TestUpdateCartItem()
    private val sut = AddCartItemUseCase(getUser, cartRepository, updateCartItem)

    @Test
    fun `EXPECT item added to cart WHEN not present`() {
        cartRepository.carts[USER_ID] = Cart(listOf(makeCartItem().copy(id = "1345")))

        sut(CART_ITEM)

        assertEquals(
            listOf(CART_ITEM),
            updateCartItem.invocations
        )
    }

    @Test
    fun `EXPECT quantity increased WHEN item already present in cart`() {
        cartRepository.carts[USER_ID] = Cart(CART_ITEMS)

        sut(CART_ITEM)

        assertEquals(
            listOf(CART_ITEM.copy(quantity = 8)),
            updateCartItem.invocations
        )
    }

    private class TestUpdateCartItem : UpdateCartItem {
        val invocations = mutableListOf<CartItem>()
        override fun invoke(cartItem: CartItem) {
            invocations.add(cartItem)
        }
    }

    private companion object {
        const val USER_ID = "1234"
        val USER = User(USER_ID, "")
        val CART_ITEM = makeCartItem(quantity = 3)
        val CART_ITEMS = listOf(CART_ITEM.copy(quantity = 5))
    }
}
