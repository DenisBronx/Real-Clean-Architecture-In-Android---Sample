package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.makeCartItem
import com.denisbrandi.androidrealca.cart.domain.repository.TestCartRepository
import com.denisbrandi.androidrealca.flow.testobserver.test
import com.denisbrandi.androidrealca.user.domain.model.User
import kotlin.test.*
import kotlinx.coroutines.flow.flowOf

class ObserveUserCartTest {
    private val testCartRepository = TestCartRepository()
    private val sut = ObserveUserCartUseCase({ USER }, testCartRepository)

    @Test
    fun `EXPECT cart updates`() {
        testCartRepository.cartUpdates[USER_ID] = flowOf(emptyList(), CART_ITEMS)

        val testObserver = sut().test()

        assertEquals(
            listOf(emptyList(), CART_ITEMS),
            testObserver.getValues()
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val USER = User(USER_ID, "")
        val CART_ITEMS = listOf(makeCartItem())
    }
}
