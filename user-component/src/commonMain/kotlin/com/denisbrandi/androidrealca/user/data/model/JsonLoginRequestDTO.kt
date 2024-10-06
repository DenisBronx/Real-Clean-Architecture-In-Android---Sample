package com.denisbrandi.androidrealca.user.data.model

import kotlinx.serialization.*

@Serializable
internal class JsonLoginRequestDTO(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)
