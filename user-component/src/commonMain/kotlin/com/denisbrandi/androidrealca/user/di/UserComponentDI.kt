package com.denisbrandi.androidrealca.user.di

import com.denisbrandi.androidrealca.cache.CacheProvider
import com.denisbrandi.androidrealca.httpclient.HttpClientProvider
import com.denisbrandi.androidrealca.user.data.repository.RealUserRepository
import com.denisbrandi.androidrealca.user.domain.usecase.*

class UserComponentDI(
    private val httpClientProvider: HttpClientProvider,
    private val cacheProvider: CacheProvider
) {

    private val userRepository by lazy {
        RealUserRepository(httpClientProvider.getClient(), cacheProvider)
    }

    val login: Login by lazy {
        LoginUseCase(userRepository)
    }
}
