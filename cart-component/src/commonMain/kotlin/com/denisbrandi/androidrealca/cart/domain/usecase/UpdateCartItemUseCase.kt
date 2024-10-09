package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import com.denisbrandi.androidrealca.cart.domain.repository.CartRepository
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser

internal class UpdateCartItemUseCase(
    private val getUser: GetUser,
    private val cartRepository: CartRepository
) : UpdateCartItem {
    override fun invoke(cartItem: CartItem) {
        cartRepository.updateCartItem(getUser().id, cartItem)
    }
}
