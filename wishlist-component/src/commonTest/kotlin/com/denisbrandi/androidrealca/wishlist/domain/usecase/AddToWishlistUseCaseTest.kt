package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.user.domain.model.User
import com.denisbrandi.androidrealca.wishlist.domain.model.makeWishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.repository.TestWishlistRepository
import kotlin.test.*

class AddToWishlistUseCaseTest {

    private val testWishlistRepository = TestWishlistRepository()
    private val sut = AddToWishlistUseCase({ USER }, testWishlistRepository)

    @Test
    fun `EXPECT delegation to repository`() {
        sut(WISHLIST_ITEM)

        assertEquals(
            listOf(USER_ID to WISHLIST_ITEM),
            testWishlistRepository.addToWishlistInvocations
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val USER = User(USER_ID, "")
        val WISHLIST_ITEM = makeWishlistItem()
    }
}
