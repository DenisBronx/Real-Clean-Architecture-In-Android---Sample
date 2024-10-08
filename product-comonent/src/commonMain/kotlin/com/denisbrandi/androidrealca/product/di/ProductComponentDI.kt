package com.denisbrandi.androidrealca.product.di

import com.denisbrandi.androidrealca.product.data.repository.RealProductRepository
import com.denisbrandi.androidrealca.product.domain.usecase.GetProducts
import io.ktor.client.HttpClient

class ProductComponentDI(
    private val httpClient: HttpClient
) {
    private val productRepository by lazy {
        RealProductRepository(httpClient)
    }

    val getProducts by lazy {
        GetProducts(productRepository::getProducts)
    }
}
