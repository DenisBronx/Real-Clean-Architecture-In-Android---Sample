package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.makeCartItem
import com.denisbrandi.androidrealca.cart.domain.repository.TestCartRepository
import com.denisbrandi.androidrealca.user.domain.model.User
import kotlin.test.*

class UpdateCartItemUseCaseTest {

    private val testCartRepository = TestCartRepository()
    private val sut = UpdateCartItemUseCase({ USER }, testCartRepository)

    @Test
    fun `EXPECT delegation to repository`() {
        sut(CART_ITEM)

        assertEquals(
            listOf(USER_ID to CART_ITEM),
            testCartRepository.updateCartItemInvocations
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val USER = User(USER_ID, "")
        val CART_ITEM = makeCartItem()
    }
}
