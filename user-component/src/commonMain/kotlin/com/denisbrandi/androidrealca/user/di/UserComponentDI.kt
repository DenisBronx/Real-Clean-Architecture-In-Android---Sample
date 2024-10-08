package com.denisbrandi.androidrealca.user.di

import com.denisbrandi.androidrealca.cache.CacheProvider
import com.denisbrandi.androidrealca.user.data.repository.RealUserRepository
import com.denisbrandi.androidrealca.user.domain.usecase.*
import io.ktor.client.HttpClient

class UserComponentDI(
    private val httpClient: HttpClient,
    private val cacheProvider: CacheProvider
) {

    private val userRepository by lazy {
        RealUserRepository(httpClient, cacheProvider)
    }

    val login: Login by lazy {
        LoginUseCase(userRepository)
    }
}
