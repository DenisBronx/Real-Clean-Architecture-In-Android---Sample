package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.flow.testobserver.test
import com.denisbrandi.androidrealca.wishlist.domain.model.*
import kotlin.test.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest

class ObserveUserWishlistIdsUseCaseTest {

    private val observeUserWishlist = TestObserveUserWishlist()
    private val sut = ObserveUserWishlistIdsUseCase(observeUserWishlist)

    @Test
    fun `EXPECT list of ids`() = runTest {
        observeUserWishlist.wishlistUpdates = flowOf(emptyList(), WISHLIST_ITEMS)

        val testObserver = sut().test()

        assertEquals(
            listOf(emptyList(), WISHLIST_ITEMS_IDS),
            testObserver.getValues()
        )
    }

    private class TestObserveUserWishlist : ObserveUserWishlist {
        lateinit var wishlistUpdates: Flow<List<WishlistItem>>
        override fun invoke(): Flow<List<WishlistItem>> = wishlistUpdates
    }

    private companion object {
        val WISHLIST_ITEMS = listOf(makeWishlistItem())
        val WISHLIST_ITEMS_IDS = listOf("1")
    }
}
