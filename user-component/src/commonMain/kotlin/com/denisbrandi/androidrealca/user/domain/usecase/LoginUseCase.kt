package com.denisbrandi.androidrealca.user.domain.usecase

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.domain.model.*
import com.denisbrandi.androidrealca.user.domain.repository.UserRepository

internal class LoginUseCase(
    private val userRepository: UserRepository
) : Login {
    override suspend fun invoke(loginRequest: LoginRequest): Answer<Unit, LoginError> {
        return when {
            !Email(loginRequest.email).isValid() -> {
                Answer.Error(LoginError.InvalidEmail)
            }

            !Password(loginRequest.password).isValid() -> {
                Answer.Error(LoginError.InvalidPassword)
            }

            else -> {
                return userRepository.login(loginRequest)
            }
        }
    }
}
