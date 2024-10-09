package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import com.denisbrandi.androidrealca.cart.domain.repository.CartRepository
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import kotlinx.coroutines.flow.Flow

internal class ObserveUserCartUseCase(
    private val getUser: GetUser,
    private val cartRepository: CartRepository
) : ObserveUserCart {
    override fun invoke(): Flow<List<CartItem>> {
        return cartRepository.observeCart(getUser().id)
    }
}
