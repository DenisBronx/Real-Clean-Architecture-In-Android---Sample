package com.denisbrandi.androidrealca.wishlist.data.repository

import com.denisbrandi.androidrealca.cache.test.TestCacheProvider
import com.denisbrandi.androidrealca.flow.testobserver.test
import com.denisbrandi.androidrealca.wishlist.data.model.JsonWishlistCacheDto
import com.denisbrandi.androidrealca.wishlist.domain.model.makeWishlistItem
import kotlin.test.*

class RealWishlistRepositoryTest {

    private val cacheProvider = TestCacheProvider(
        "wishlist-cache",
        JsonWishlistCacheDto(emptyMap())
    )
    private val sut = RealWishlistRepository(cacheProvider)

    @Test
    fun `EXPECT data saved and wishlist updates emitted`() {
        val wishlistObserver = sut.observeWishlist(USER_ID).test()

        sut.addToWishlist(USER_ID, WISHLIST_ITEM)

        assertEquals(
            listOf(emptyList(), listOf(WISHLIST_ITEM)),
            wishlistObserver.getValues()
        )
    }

    @Test
    fun `EXPECT data added and wishlist updates emitted`() {
        val wishlistObserver = sut.observeWishlist(USER_ID).test()
        sut.addToWishlist(USER_ID, WISHLIST_ITEM)
        sut.addToWishlist(USER_ID, WISHLIST_ITEM.copy(id = "2"))

        assertEquals(
            listOf(
                emptyList(),
                listOf(WISHLIST_ITEM),
                listOf(WISHLIST_ITEM, WISHLIST_ITEM.copy(id = "2"))
            ),
            wishlistObserver.getValues()
        )
    }

    @Test
    fun `EXPECT no new emissions WHEN item to add is already in wishlist`() {
        val wishlistObserver = sut.observeWishlist(USER_ID).test()
        sut.addToWishlist(USER_ID, WISHLIST_ITEM)
        sut.addToWishlist(USER_ID, WISHLIST_ITEM)

        assertEquals(
            listOf(
                emptyList(),
                listOf(WISHLIST_ITEM),
            ),
            wishlistObserver.getValues()
        )
    }

    @Test
    fun `EXPECT no new emissions WHEN item to remove is not in wishlist`() {
        val wishlistObserver = sut.observeWishlist(USER_ID).test()
        sut.addToWishlist(USER_ID, WISHLIST_ITEM)

        sut.removeFromWishlist(USER_ID, "id not present")

        assertEquals(
            listOf(
                emptyList(),
                listOf(WISHLIST_ITEM),
            ),
            wishlistObserver.getValues()
        )
    }

    @Test
    fun `EXPECT data removed WHEN item to remove is in wishlist`() {
        val wishlistObserver = sut.observeWishlist(USER_ID).test()
        sut.addToWishlist(USER_ID, WISHLIST_ITEM)

        sut.removeFromWishlist(USER_ID, WISHLIST_ITEM.id)

        assertEquals(
            listOf(
                emptyList(),
                listOf(WISHLIST_ITEM),
                emptyList()
            ),
            wishlistObserver.getValues()
        )
    }

    private companion object {
        const val USER_ID = "1234"
        val WISHLIST_ITEM = makeWishlistItem()
    }
}
