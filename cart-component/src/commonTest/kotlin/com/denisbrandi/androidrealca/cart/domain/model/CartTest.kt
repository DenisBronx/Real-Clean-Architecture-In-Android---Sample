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

    @Test
    fun `EXPECT 0 items WHEN cart is empty`() {
        val sut = Cart(emptyList())

        val result = sut.getNumberOfItems()

        assertEquals(0, result)
    }

    @Test
    fun `EXPECT number of items WHEN all items have 1 quantity`() {
        val sut = Cart(listOf(makeCartItem(), makeCartItem()))

        val result = sut.getNumberOfItems()

        assertEquals(2, result)
    }

    @Test
    fun `EXPECT number of items x their quantity WHEN items have quantity 1+`() {
        val sut = Cart(listOf(makeCartItem(quantity = 4), makeCartItem(quantity = 5)))

        val result = sut.getNumberOfItems()

        assertEquals(9, result)
    }
}
