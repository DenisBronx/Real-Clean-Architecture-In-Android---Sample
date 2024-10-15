package com.denisbrandi.androidrealca.cart.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money

data class Cart(val cartItems: List<CartItem>) {
    fun getSubtotal(): Money? {
        return if (cartItems.isNotEmpty()) {
            val currency = cartItems[0].money.currencySymbol
            var subtotal = 0.0
            cartItems.forEach {
                subtotal += it.money.amount * it.quantity
            }
            Money(subtotal, currency)
        } else {
            null
        }
    }
}
