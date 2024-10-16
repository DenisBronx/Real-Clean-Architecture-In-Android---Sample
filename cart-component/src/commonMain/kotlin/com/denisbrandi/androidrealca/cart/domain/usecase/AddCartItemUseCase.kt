package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import com.denisbrandi.androidrealca.cart.domain.repository.CartRepository
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser

internal class AddCartItemUseCase(
    private val getUser: GetUser,
    private val cartRepository: CartRepository,
    private val updateCartItem: UpdateCartItem
) : AddCartItem {
    override fun invoke(cartItem: CartItem) {
        val cartItemInCart = cartRepository.getCart(getUser().id).cartItems.find {
            it.id == cartItem.id
        }
        if (cartItemInCart != null) {
            updateCartItem(cartItemInCart.copy(quantity = cartItemInCart.quantity + cartItem.quantity))
        } else {
            updateCartItem(cartItem)
        }
    }
}
