package com.denisbrandi.androidrealca.di

import android.content.Context
import com.denisbrandi.androidrealca.cart.di.CartComponentDI
import com.denisbrandi.androidrealca.httpclient.RealHttpClientProvider
import com.denisbrandi.androidrealca.onboarding.di.OnboardingUIDI
import com.denisbrandi.androidrealca.product.di.ProductComponentDI
import com.denisbrandi.androidrealca.user.di.UserComponentDI
import com.denisbrandi.androidrealca.wishlist.di.WishlistComponentDI

object Injection {

    fun start(
        applicationContext: Context
    ) {
        val httpClient = RealHttpClientProvider.getClient()
        val cacheProvider = AndroidCacheProvider(applicationContext)
        val userComponentDI = UserComponentDI(httpClient, cacheProvider)
        val productComponentDI = ProductComponentDI(httpClient)
        val wishlistComponentDI = WishlistComponentDI(cacheProvider, userComponentDI.getUser)
        val cartComponentDI = CartComponentDI(cacheProvider, userComponentDI.getUser)
        val onboardingUIDI = OnboardingUIDI(userComponentDI.login)
    }
}
