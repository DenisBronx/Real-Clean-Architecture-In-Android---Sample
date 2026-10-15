package com.denisbrandi.androidrealca.product.di

import com.denisbrandi.androidrealca.product.data.repository.RealProductRepository
import com.denisbrandi.androidrealca.product.domain.usecase.GetProducts
import io.ktor.client.*

class ProductComponentAssembler(
    private val httpClient: HttpClient
) {
    private val productRepository by lazy {
        RealProductRepository(httpClient)
    }

    fun makeGetProducts() = GetProducts(productRepository::getProducts)
}
