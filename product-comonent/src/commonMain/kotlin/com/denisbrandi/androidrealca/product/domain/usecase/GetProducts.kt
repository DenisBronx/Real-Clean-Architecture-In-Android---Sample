package com.denisbrandi.androidrealca.product.domain.usecase

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.product.domain.model.Product

fun interface GetProducts {
    suspend operator fun invoke(): Answer<List<Product>, Unit>
}
