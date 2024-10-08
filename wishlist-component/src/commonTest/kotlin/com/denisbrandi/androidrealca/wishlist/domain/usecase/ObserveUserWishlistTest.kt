package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.flow.testobserver.test
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.user.domain.model.User
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.repository.TestWishlistRepository
import kotlin.test.*
import kotlinx.coroutines.flow.flowOf

class ObserveUserWishlistTest {
    private val testWishlistRepository = TestWishlistRepository()
    private val sut = ObserveUserWishlistUseCase({ USER }, testWishlistRepository)

    @Test
    fun `EXPECT wishlist updates`() {
        testWishlistRepository.wishlistUpdates[USER_ID] = flowOf(emptyList(), WISHLIST_ITEMS)

        val testObserver = sut().test()

        assertEquals(
            listOf(emptyList(), WISHLIST_ITEMS),
            testObserver.getValues()
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val USER = User(USER_ID, "")
        val WISHLIST_ITEMS = listOf(
            WishlistItem(
                id = "1",
                name = "Wireless Headphones",
                money = Money(99.99, "$"),
                imageUrl = "https://example.com/images/wireless-headphones.jpg"
            )
        )
    }
}
