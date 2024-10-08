package com.denisbrandi.androidrealca.wishlist.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money

data class WishlistItem(
    val id: String,
    val name: String,
    val money: Money,
    val imageUrl: String
)
