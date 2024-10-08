package com.denisbrandi.androidrealca.product.data.model

import kotlinx.serialization.*

@Serializable
data class JsonProductResponseDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Double,
    @SerialName("currency") val currency: String,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("quantityAvailable") val quantityAvailable: Int
)
