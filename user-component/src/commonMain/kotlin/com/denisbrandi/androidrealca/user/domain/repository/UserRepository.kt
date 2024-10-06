package com.denisbrandi.androidrealca.user.domain.repository

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.domain.model.*

internal interface UserRepository {
    suspend fun login(loginRequest: LoginRequest): Answer<Unit, LoginError>
}
