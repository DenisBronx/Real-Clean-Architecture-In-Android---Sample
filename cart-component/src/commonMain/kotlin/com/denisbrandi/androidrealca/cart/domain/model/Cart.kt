package com.denisbrandi.androidrealca.cart.domain.model

data class Cart(val cartItems: List<CartItem>) {
    fun getSubtotal(): Double? {
        return if (cartItems.isNotEmpty()) {
            var subtotal = 0.0
            cartItems.forEach {
                subtotal += it.money.amount * it.quantity
            }
            subtotal
        } else {
            null
        }
    }

    fun getCurrency(): String? {
        return if (cartItems.isNotEmpty()) {
            cartItems[0].money.currencySymbol
        } else {
            null
        }
    }
}
