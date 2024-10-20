package com.denisbrandi.androidrealca.cart.data.repository

import com.denisbrandi.androidrealca.cache.test.TestCacheProvider
import com.denisbrandi.androidrealca.cart.data.model.JsonCartCacheDto
import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.flow.testobserver.test
import kotlin.test.*

class RealCartRepositoryTest {

    private val cacheProvider = TestCacheProvider(
        "cart-cache",
        JsonCartCacheDto(emptyMap())
    )
    private val sut = RealCartRepository(cacheProvider)

    @Test
    fun `EXPECT data saved and cart updates emitted`() {
        val cartObserver = sut.observeCart(USER_ID).test()

        sut.updateCartItem(USER_ID, CART_ITEM)

        val finalCart = Cart(listOf(CART_ITEM))
        assertEquals(finalCart, sut.getCart(USER_ID))
        assertEquals(
            listOf(Cart(emptyList()), finalCart),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT data added and cart updates emitted`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM.copy(id = "2"))

        val finalCart = Cart(listOf(CART_ITEM, CART_ITEM.copy(id = "2")))
        assertEquals(finalCart, sut.getCart(USER_ID))
        assertEquals(
            listOf(
                Cart(emptyList()),
                Cart(listOf(CART_ITEM)),
                finalCart
            ),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT quantity updated and cart updates emitted WHEN quantity is different`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM.copy(quantity = 2))

        val finalCart = Cart(listOf(CART_ITEM.copy(quantity = 2)))
        assertEquals(finalCart, sut.getCart(USER_ID))
        assertEquals(
            listOf(
                Cart(emptyList()),
                Cart(listOf(CART_ITEM)),
                finalCart
            ),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT no updates WHEN quantity is the same`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM)

        val finalCart = Cart(listOf(CART_ITEM))
        assertEquals(finalCart, sut.getCart(USER_ID))
        assertEquals(
            listOf(
                Cart(emptyList()),
                finalCart
            ),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT data removed and cart updates emitted WHEN quantity is 0`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM.copy(quantity = 0))

        val finalCart = Cart(emptyList())
        assertEquals(finalCart, sut.getCart(USER_ID))
        assertEquals(
            listOf(
                Cart(emptyList()),
                Cart(listOf(CART_ITEM)),
                finalCart
            ),
            cartObserver.getValues()
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val CART_ITEM = makeCartItem()
    }
}
