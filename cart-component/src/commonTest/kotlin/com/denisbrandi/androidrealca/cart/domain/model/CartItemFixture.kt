package com.denisbrandi.androidrealca.cart.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money

fun makeCartItem(
    amount: Double = 99.99,
    quantity: Int = 1
): CartItem {
    return CartItem(
        id = "1",
        name = "Wireless Headphones",
        money = Money(amount, "$"),
        imageUrl = "https://example.com/images/wireless-headphones.jpg",
        quantity = quantity
    )
}
