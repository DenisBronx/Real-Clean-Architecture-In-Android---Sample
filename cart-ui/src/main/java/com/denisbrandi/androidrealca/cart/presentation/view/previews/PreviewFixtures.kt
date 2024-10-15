package com.denisbrandi.androidrealca.cart.presentation.view.previews

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import com.denisbrandi.androidrealca.money.domain.model.Money

val cartItems = listOf(
    CartItem(
        "1",
        "Wireless Headphones",
        Money(99.99, "$"),
        "https://m.media-amazon.com/images/I/61fU3njgzZL._AC_SL1500_.jpg",
        quantity = 1
    ),
    CartItem(
        "2",
        "Smartphone Stand",
        Money(15.49, "$"),
        "https://m.media-amazon.com/images/I/61shuq7IDcL._AC_SL1500_.jpg",
        quantity = 5
    ),
    CartItem(
        "3",
        "Bluetooth Speaker",
        Money(79.99, "$"),
        "https://m.media-amazon.com/images/I/81mr1duG3ZL._AC_SL1500_.jpg",
        quantity = 8
    ),
    CartItem(
        "4",
        "Portable Charger",
        Money(24.99, "$"),
        "https://m.media-amazon.com/images/I/61cAlFkKsyL._AC_SL1500_.jpg",
        quantity = 16
    ),
    CartItem(
        "5",
        "Wireless Mouse",
        Money(29.99, "$"),
        "https://m.media-amazon.com/images/I/61N+CzcA8vL._AC_SL1500_.jpg",
        quantity = 1
    ),
    CartItem(
        "6",
        "USB-C Cable",
        Money(12.99, "$"),
        "https://m.media-amazon.com/images/I/61wYXznLNtL._SL1500_.jpg",
        quantity = 1
    ),
    CartItem(
        "7",
        "Laptop Sleeve",
        Money(34.99, "$"),
        "https://m.media-amazon.com/images/I/819Ook6vDGL._AC_SL1300_.jpg",
        quantity = 1
    ),
    CartItem(
        "8",
        "Smartwatch",
        Money(199.99, "$"),
        "https://m.media-amazon.com/images/I/71Ggfcmy2cL._AC_SL1500_.jpg",
        quantity = 1
    ),
    CartItem(
        "9",
        "Noise Cancelling Earbuds",
        Money(149.99, "$"),
        "https://m.media-amazon.com/images/I/719T7V6DWWL._AC_SL1500_.jpg",
        quantity = 1
    ),
    CartItem(
        "10",
        "Gaming Keyboard",
        Money(89.99, "$"),
        "https://m.media-amazon.com/images/I/71wBsNJdfdL._AC_SL1500_.jpg",
        quantity = 1
    )
)
