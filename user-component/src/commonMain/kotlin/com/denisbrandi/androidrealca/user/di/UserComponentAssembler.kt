package com.denisbrandi.androidrealca.user.di

import com.denisbrandi.androidrealca.cache.CacheProvider
import com.denisbrandi.androidrealca.user.data.repository.RealUserRepository
import com.denisbrandi.androidrealca.user.domain.usecase.*
import io.ktor.client.*

class UserComponentAssembler(
    private val httpClient: HttpClient,
    private val cacheProvider: CacheProvider
) {

    private val userRepository by lazy {
        RealUserRepository(httpClient, cacheProvider)
    }

    fun makeLogin(): Login = LoginUseCase(userRepository)

    fun makeGetUser() = GetUser(userRepository::getUser)

    fun makeIsUserLoggedIn() = IsUserLoggedIn(userRepository::isLoggedIn)
}
