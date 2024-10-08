package com.denisbrandi.androidrealca.wishlist.data.model

import kotlinx.serialization.*

@Serializable
data class JsonWishlistCacheDto(
    @SerialName("usersWishlist") val usersWishlist: Map<String, List<JsonWishlistItemCacheDTO>>
)

@Serializable
data class JsonWishlistItemCacheDTO(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
    @SerialName("currency") val currency: String,
    @SerialName("imageUrl") val imageUrl: String
)
