package com.denisbrandi.androidrealca.cart.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money
import kotlin.test.*

class CartTest {

    @Test
    fun `EXPECT null subtotal and currency WHEN cart is empty`() {
        val sut = Cart(emptyList())

        assertNull(sut.getSubtotal())
        assertNull(sut.getCurrency())
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

        assertEquals(150.99, sut.getSubtotal())
        assertEquals("$", sut.getCurrency())
    }

    fun makeCartItem(amount: Double, quantity: Int): CartItem {
        return CartItem("", "", Money(amount, "$"), "", quantity)
    }
}
