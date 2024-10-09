package com.denisbrandi.androidrealca.product.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money

data class Product(
    val id: String,
    val name: String,
    val money: Money,
    val imageUrl: String
)
