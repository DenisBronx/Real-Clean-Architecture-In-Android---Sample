package com.denisbrandi.androidrealca.user.domain.usecase

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.domain.model.*

fun interface Login {
    suspend operator fun invoke(loginRequest: LoginRequest): Answer<Unit, LoginError>
}
