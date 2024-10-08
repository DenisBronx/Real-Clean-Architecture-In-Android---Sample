package com.denisbrandi.androidrealca.user.domain.usecase

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.domain.model.*

fun interface Login {
    suspend operator fun invoke(loginRequest: LoginRequest): Answer<Unit, LoginError>
}

fun interface GetUser {
    operator fun invoke(): User
}

fun interface IsUserLoggedIn {
    operator fun invoke(): Boolean
}
