package com.denisbrandi.androidrealca.cart.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money
import kotlin.test.*

class CartTest {

    @Test
    fun `EXPECT null subtotal and currency WHEN cart is empty`() {
        val sut = Cart(emptyList())

        val result = sut.getSubtotal()

        assertNull(result)
    }

    @Test
    fun `EXPECT sum of product per their quantity WHEN cart is not empty`() {
        val sut = Cart(
            listOf(
                makeCartItem(0.99, 1),
                makeCartItem(25.00, 3),
                makeCartItem(30.00, 2),
                makeCartItem(15.00, 1)
            )
        )

        val result = sut.getSubtotal()

        assertEquals(Money(150.99, "$"), result)
    }

    fun makeCartItem(amount: Double, quantity: Int): CartItem {
        return CartItem("", "", Money(amount, "$"), "", quantity)
    }
}
