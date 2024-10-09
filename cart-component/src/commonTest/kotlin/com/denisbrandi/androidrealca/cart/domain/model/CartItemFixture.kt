package com.denisbrandi.androidrealca.cart.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money

fun makeCartItem(): CartItem {
    return CartItem(
        id = "1",
        name = "Wireless Headphones",
        money = Money(99.99, "$"),
        imageUrl = "https://example.com/images/wireless-headphones.jpg",
        quantity = 1
    )
}
