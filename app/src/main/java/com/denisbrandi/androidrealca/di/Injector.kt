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
        wishlistComponentDI,
        cartComponentDI.addCartItem
    )
    val wishlistUIDI = WishlistUIDI(wishlistComponentDI, cartComponentDI.addCartItem)
    val cartUIDI = CartUIDI(cartComponentDI)
    val mainUIDI = MainUIDI(
        wishlistComponentDI.observeUserWishlistIds,
        cartComponentDI.observeUserCart
    )

    companion object {
        lateinit var INSTANCE: Injector

        fun start(applicationContext: Context) {
            INSTANCE = Injector(applicationContext)
        }
    }
}

val injector = Injector.INSTANCE
