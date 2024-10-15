package com.denisbrandi.androidrealca.di

import android.content.Context
import com.denisbrandi.androidrealca.cart.di.CartComponentDI
import com.denisbrandi.androidrealca.httpclient.RealHttpClientProvider
import com.denisbrandi.androidrealca.onboarding.di.OnboardingUIDI
import com.denisbrandi.androidrealca.plp.di.PLPUIDI
import com.denisbrandi.androidrealca.product.di.ProductComponentDI
import com.denisbrandi.androidrealca.user.di.UserComponentDI
import com.denisbrandi.androidrealca.wishlist.di.WishlistComponentDI

class Injector private constructor(
    private val applicationContext: Context
) {
    private val httpClient = RealHttpClientProvider.getClient()
    private val cacheProvider = AndroidCacheProvider(applicationContext)
    private val userComponentDI = UserComponentDI(httpClient, cacheProvider)
    private val productComponentDI = ProductComponentDI(httpClient)
    private val wishlistComponentDI = WishlistComponentDI(cacheProvider, userComponentDI.getUser)
    private val cartComponentDI = CartComponentDI(cacheProvider, userComponentDI.getUser)
    val isUserLoggedIn = userComponentDI.isUserLoggedIn
    val onboardingUIDI = OnboardingUIDI(userComponentDI.login)
    val plpUIDI = PLPUIDI(
        userComponentDI.getUser,
        productComponentDI.getProducts,
        wishlistComponentDI
    )

    companion object {
        lateinit var INSTANCE: Injector

        fun start(applicationContext: Context) {
            INSTANCE = Injector(applicationContext)
        }
    }
}

val injector = Injector.INSTANCE
