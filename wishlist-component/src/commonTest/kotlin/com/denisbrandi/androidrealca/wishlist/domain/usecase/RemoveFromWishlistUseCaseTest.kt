package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.user.domain.model.User
import com.denisbrandi.androidrealca.wishlist.domain.repository.TestWishlistRepository
import kotlin.test.*

class RemoveFromWishlistUseCaseTest {
    private val testWishlistRepository = TestWishlistRepository()
    private val sut = RemoveFromWishlistUseCase({ USER }, testWishlistRepository)

    @Test
    fun `EXPECT delegation to repository`() {
        sut(WISHLIST_ITEM_ID)

        assertEquals(
            listOf(USER_ID to WISHLIST_ITEM_ID),
            testWishlistRepository.removeFromWishlistInvocations
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val USER = User(USER_ID, "")
        const val WISHLIST_ITEM_ID = "1"
    }
}
