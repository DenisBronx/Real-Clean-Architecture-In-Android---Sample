package com.denisbrandi.androidrealca.user.domain.usecase

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.domain.model.*
import com.denisbrandi.androidrealca.user.domain.repository.TestUserRepository
import kotlin.test.*
import kotlinx.coroutines.test.runTest

class LoginUseCaseTest {

    private val userRepository = TestUserRepository().also {
        it.expectedLoginRequest = VALID_LOGIN_REQUEST
    }
    private val sut = LoginUseCase(userRepository)

    @Test
    fun `EXPECT invalid email error WHEN login request has invalid email`() = runTest {
        val loginRequestWithInvalidEmail = LoginRequest("", "validPassword")

        val result = sut(loginRequestWithInvalidEmail)

        assertEquals(Answer.Error(LoginError.InvalidEmail), result)
    }

    @Test
    fun `EXPECT invalid password error WHEN login request has invalid password`() = runTest {
        val loginRequestWithInvalidPassword = LoginRequest("valid@email.com", "")

        val result = sut(loginRequestWithInvalidPassword)

        assertEquals(Answer.Error(LoginError.InvalidPassword), result)
    }

    @Test
    fun `EXPECT success WHEN login request is valid and repository returns success`() = runTest {
        val repositoryResult = Answer.Success(Unit)
        userRepository.loginResult = repositoryResult

        val result = sut(VALID_LOGIN_REQUEST)

        assertEquals(repositoryResult, result)
    }

    @Test
    fun `EXPECT error WHEN login request is valid and repository returns error`() = runTest {
        val repositoryResult = Answer.Error(LoginError.GenericError)
        userRepository.loginResult = repositoryResult

        val result = sut(VALID_LOGIN_REQUEST)

        assertEquals(repositoryResult, result)
    }

    private companion object {
        val VALID_LOGIN_REQUEST = LoginRequest("valid@email.com", "validPassword")
    }

}
