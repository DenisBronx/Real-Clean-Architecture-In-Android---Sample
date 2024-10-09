package com.denisbrandi.androidrealca.cart.data.repository

import com.denisbrandi.androidrealca.cache.test.TestCacheProvider
import com.denisbrandi.androidrealca.cart.data.model.JsonCartCacheDto
import com.denisbrandi.androidrealca.cart.domain.model.makeCartItem
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

        assertEquals(
            listOf(emptyList(), listOf(CART_ITEM)),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT data added and cart updates emitted`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM.copy(id = "2"))

        assertEquals(
            listOf(
                emptyList(),
                listOf(CART_ITEM),
                listOf(CART_ITEM, CART_ITEM.copy(id = "2"))
            ),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT quantity updated and cart updates emitted WHEN quantity is different`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM.copy(quantity = 2))

        assertEquals(
            listOf(
                emptyList(),
                listOf(CART_ITEM),
                listOf(CART_ITEM.copy(quantity = 2))
            ),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT no updates WHEN quantity is the same`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM)

        assertEquals(
            listOf(
                emptyList(),
                listOf(CART_ITEM)
            ),
            cartObserver.getValues()
        )
    }

    @Test
    fun `EXPECT data removed and cart updates emitted WHEN quantity is 0`() {
        val cartObserver = sut.observeCart(USER_ID).test()
        sut.updateCartItem(USER_ID, CART_ITEM)

        sut.updateCartItem(USER_ID, CART_ITEM.copy(quantity = 0))

        assertEquals(
            listOf(
                emptyList(),
                listOf(CART_ITEM),
                emptyList()
            ),
            cartObserver.getValues()
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val CART_ITEM = makeCartItem()
    }
}
