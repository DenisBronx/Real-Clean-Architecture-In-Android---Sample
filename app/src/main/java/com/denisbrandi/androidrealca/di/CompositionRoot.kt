package com.denisbrandi.androidrealca.di

import android.content.Context
import com.denisbrandi.androidrealca.cart.di.*
import com.denisbrandi.androidrealca.httpclient.RealHttpClientProvider
import com.denisbrandi.androidrealca.main.di.MainUIAssembler
import com.denisbrandi.androidrealca.onboarding.di.OnboardingUIAssembler
import com.denisbrandi.androidrealca.plp.di.PLPUIAssembler
import com.denisbrandi.androidrealca.product.di.ProductComponentAssembler
import com.denisbrandi.androidrealca.user.di.UserComponentAssembler
import com.denisbrandi.androidrealca.wishlist.di.*

class CompositionRoot private constructor(
    applicationContext: Context
) {
    private val httpClient by lazy {
        RealHttpClientProvider.getClient()
    }
    private val cacheProvider by lazy {
        AndroidCacheProvider(applicationContext)
    }
    private val userComponentAssembler by lazy {
        UserComponentAssembler(httpClient, cacheProvider)
    }
    private val productComponentAssembler by lazy {
        ProductComponentAssembler(httpClient)
    }
    private val wishlistComponentAssembler by lazy {
        WishlistComponentAssembler(cacheProvider, userComponentAssembler)
    }
    private val cartComponentAssembler by lazy {
        CartComponentAssembler(cacheProvider, userComponentAssembler)
    }
    val isUserLoggedIn by lazy {
        userComponentAssembler.makeIsUserLoggedIn()
    }
    val onboardingUIAssembler by lazy {
        OnboardingUIAssembler(userComponentAssembler)
    }
    val plpUIAssembler by lazy {
        PLPUIAssembler(
            userComponentAssembler,
            productComponentAssembler,
            wishlistComponentAssembler,
            cartComponentAssembler
        )
    }
    val wishlistUIAssembler by lazy {
        WishlistUIAssembler(wishlistComponentAssembler, cartComponentAssembler)
    }
    val cartUIAssembler by lazy {
        CartUIAssembler(cartComponentAssembler)
    }
    val mainUIAssembler by lazy {
        MainUIAssembler(wishlistComponentAssembler, cartComponentAssembler)
    }

    companion object {
        lateinit var INSTANCE: CompositionRoot

        fun compose(applicationContext: Context) {
            INSTANCE = CompositionRoot(applicationContext)
        }
    }
}

val compositionRoot = CompositionRoot.INSTANCE
