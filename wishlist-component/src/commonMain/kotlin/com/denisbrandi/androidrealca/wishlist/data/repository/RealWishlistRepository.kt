package com.denisbrandi.androidrealca.wishlist.data.repository

import com.denisbrandi.androidrealca.cache.*
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.wishlist.data.model.*
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.*

internal class RealWishlistRepository(
    private val cacheProvider: CacheProvider
) : WishlistRepository {

    private val flowCachedObject: FlowCachedObject<JsonWishlistCacheDto> by lazy {
        cacheProvider.getFlowCachedObject(
            fileName = "wishlist-cache",
            serializer = JsonWishlistCacheDto.serializer(),
            defaultValue = JsonWishlistCacheDto(emptyMap())
        )
    }

    override fun addToWishlist(userId: String, wishlistItem: WishlistItem) {
        val updatedCache = getUpdatedCacheForUser(userId) { userWishlist ->
            if (userWishlist.find { it.id == wishlistItem.id } == null) {
                userWishlist.add(mapToDto(wishlistItem))
            }
        }
        flowCachedObject.put(updatedCache)
    }

    override fun removeFromWishlist(userId: String, wishlistItemId: String) {
        val updatedCache = getUpdatedCacheForUser(userId) { userWishlist ->
            userWishlist.find { it.id == wishlistItemId }?.let { itemToRemove ->
                userWishlist.remove(itemToRemove)
            }
        }
        flowCachedObject.put(updatedCache)
    }

    private fun getUpdatedCacheForUser(
        userId: String,
        onUserWishlist: (MutableList<JsonWishlistItemCacheDTO>) -> Unit
    ): JsonWishlistCacheDto {
        val usersWishlist = flowCachedObject.get().usersWishlist
        val userWishlist = usersWishlist[userId].orEmpty().toMutableList()
        return JsonWishlistCacheDto(
            usersWishlist = usersWishlist.toMutableMap().apply {
                put(
                    userId,
                    userWishlist.apply {
                        onUserWishlist(this)
                    }.toList()
                )
            }
        )
    }

    override fun observeWishlist(userId: String): Flow<List<WishlistItem>> {
        return flowCachedObject.observe().map { cachedDto ->
            mapToWishlistItems(cachedDto.usersWishlist[userId] ?: emptyList())
        }
    }

    private fun mapToWishlistItems(dtos: List<JsonWishlistItemCacheDTO>): List<WishlistItem> {
        return dtos.map { dto ->
            WishlistItem(
                id = dto.id,
                name = dto.name,
                money = Money(dto.price, dto.currency),
                imageUrl = dto.imageUrl
            )
        }
    }

    private fun mapToDto(wishlistItem: WishlistItem): JsonWishlistItemCacheDTO {
        return JsonWishlistItemCacheDTO(
            id = wishlistItem.id,
            name = wishlistItem.name,
            price = wishlistItem.money.amount,
            currency = wishlistItem.money.currencySymbol,
            imageUrl = wishlistItem.imageUrl
        )
    }
}
