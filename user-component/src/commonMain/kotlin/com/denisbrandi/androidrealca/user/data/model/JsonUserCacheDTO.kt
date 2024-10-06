package com.denisbrandi.androidrealca.user.data.model

import kotlinx.serialization.*

@Serializable
internal data class JsonUserCacheDTO(
    @SerialName("id") val id: String,
    @SerialName("fullName") val fullName: String
)
