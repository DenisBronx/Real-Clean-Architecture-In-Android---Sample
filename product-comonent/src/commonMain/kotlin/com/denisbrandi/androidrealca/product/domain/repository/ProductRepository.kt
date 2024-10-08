package com.denisbrandi.androidrealca.product.domain.repository

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.product.domain.model.Product

internal interface ProductRepository {
    suspend fun getProducts(): Answer<List<Product>, Unit>
}
