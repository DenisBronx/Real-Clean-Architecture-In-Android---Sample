package com.denisbrandi.androidrealca.user.domain.repository

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.domain.model.*

class TestUserRepository : UserRepository {
    lateinit var expectedLoginRequest: LoginRequest
    lateinit var loginResult: Answer<Unit, LoginError>

    override suspend fun login(loginRequest: LoginRequest): Answer<Unit, LoginError> {
        return if (expectedLoginRequest == loginRequest) {
            loginResult
        } else {
            throw IllegalStateException("method called with not stubbed parameters")
        }
    }

    override fun getUser(): User {
        TODO("Not yet implemented")
    }

    override fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }
}
