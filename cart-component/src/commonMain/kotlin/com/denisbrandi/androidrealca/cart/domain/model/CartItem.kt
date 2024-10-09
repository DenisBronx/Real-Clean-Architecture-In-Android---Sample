package com.denisbrandi.androidrealca.cart.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money

data class CartItem(
    val id: String,
    val name: String,
    val money: Money,
    val imageUrl: String,
    val quantity: Int
)
