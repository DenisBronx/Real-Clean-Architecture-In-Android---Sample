package com.denisbrandi.androidrealca.di

import android.content.Context
import com.denisbrandi.androidrealca.cart.di.*
import com.denisbrandi.androidrealca.httpclient.RealHttpClientProvider
import com.denisbrandi.androidrealca.main.di.MainUIDI
import com.denisbrandi.androidrealca.onboarding.di.OnboardingUIDI
import com.denisbrandi.androidrealca.plp.di.PLPUIDI
import com.denisbrandi.androidrealca.product.di.ProductComponentDI
import com.denisbrandi.androidrealca.user.di.UserComponentDI
import com.denisbrandi.androidrealca.wishlist.di.*

class Injector private constructor(
    applicationContext: Context
) {
    private val httpClient by lazy {
        RealHttpClientProvider.getClient()
    }
    private val cacheProvider by lazy {
        AndroidCacheProvider(applicationContext)
    }
    private val userComponentDI by lazy {
        UserComponentDI(httpClient, cacheProvider)
    }
    private val productComponentDI by lazy {
        ProductComponentDI(httpClient)
    }
    private val wishlistComponentDI by lazy {
        WishlistComponentDI(cacheProvider, userComponentDI.getUser)
    }
    private val cartComponentDI by lazy {
        CartComponentDI(cacheProvider, userComponentDI.getUser)
    }
    val isUserLoggedIn by lazy {
        userComponentDI.isUserLoggedIn
    }
    val onboardingUIDI by lazy {
        OnboardingUIDI(userComponentDI.login)
    }
    val plpUIDI by lazy {
        PLPUIDI(
            userComponentDI.getUser,
            productComponentDI.getProducts,
            wishlistComponentDI,
            cartComponentDI.addCartItem
        )
    }
    val wishlistUIDI by lazy {
        WishlistUIDI(wishlistComponentDI, cartComponentDI.addCartItem)
    }
    val cartUIDI by lazy {
        CartUIDI(cartComponentDI)
    }
    val mainUIDI by lazy {
        MainUIDI(wishlistComponentDI.observeUserWishlistIds, cartComponentDI.observeUserCart)
    }

    companion object {
        lateinit var INSTANCE: Injector

        fun start(applicationContext: Context) {
            INSTANCE = Injector(applicationContext)
        }
    }
}

val injector = Injector.INSTANCE
