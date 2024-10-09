package com.denisbrandi.androidrealca.cart.data.model

import kotlinx.serialization.*

@Serializable
data class JsonCartCacheDto(
    @SerialName("usersWishlist") val usersCart: Map<String, List<JsonCartItemCacheDto>>
)

@Serializable
data class JsonCartItemCacheDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
    @SerialName("currency") val currency: String,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("quantity") val quantity: Int
)
