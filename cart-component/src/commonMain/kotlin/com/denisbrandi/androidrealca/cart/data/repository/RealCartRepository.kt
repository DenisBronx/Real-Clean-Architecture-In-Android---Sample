package com.denisbrandi.androidrealca.cart.data.repository

import com.denisbrandi.androidrealca.cache.*
import com.denisbrandi.androidrealca.cart.data.model.*
import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.domain.repository.CartRepository
import com.denisbrandi.androidrealca.money.domain.model.Money
import kotlinx.coroutines.flow.*

internal class RealCartRepository(
    private val cacheProvider: CacheProvider
) : CartRepository {

    private val flowCachedObject: FlowCachedObject<JsonCartCacheDto> by lazy {
        cacheProvider.getFlowCachedObject(
            fileName = "cart-cache",
            serializer = JsonCartCacheDto.serializer(),
            defaultValue = JsonCartCacheDto(emptyMap())
        )
    }

    override fun updateCartItem(userId: String, cartItem: CartItem) {
        val updatedCache = getUpdatedCacheForUser(userId) { userCart ->
            val cartItemInCache = userCart.find { it.id == cartItem.id }
            val cartItemDto = mapToDto(cartItem)
            if (cartItemInCache != null) {
                if (cartItem.quantity == 0) {
                    userCart.remove(cartItemInCache)
                } else {
                    val index = userCart.indexOf(cartItemInCache)
                    userCart[index] = cartItemDto
                }
            } else {
                userCart.add(cartItemDto)
            }
        }
        flowCachedObject.put(updatedCache)
    }

    private fun getUpdatedCacheForUser(
        userId: String,
        onUserCart: (MutableList<JsonCartItemCacheDto>) -> Unit
    ): JsonCartCacheDto {
        val usersCart = flowCachedObject.get().usersCart
        val userCart = usersCart[userId].orEmpty().toMutableList()
        return JsonCartCacheDto(
            usersCart = usersCart.toMutableMap().apply {
                put(
                    userId,
                    userCart.apply {
                        onUserCart(this)
                    }.toList()
                )
            }
        )
    }

    override fun observeCart(userId: String): Flow<Cart> {
        return flowCachedObject.observe().map { cachedDto ->
            mapToCart(userId, cachedDto)
        }
    }

    override fun getCart(userId: String): Cart {
        return mapToCart(userId, flowCachedObject.get())
    }

    private fun mapToCart(userId: String, cachedDto: JsonCartCacheDto): Cart {
        return Cart(
            mapToCartItems(cachedDto.usersCart[userId] ?: emptyList())
        )
    }

    private fun mapToCartItems(dtos: List<JsonCartItemCacheDto>): List<CartItem> {
        return dtos.map { dto ->
            CartItem(
                id = dto.id,
                name = dto.name,
                money = Money(dto.price, dto.currency),
                imageUrl = dto.imageUrl,
                quantity = dto.quantity
            )
        }
    }

    private fun mapToDto(cartItem: CartItem): JsonCartItemCacheDto {
        return JsonCartItemCacheDto(
            id = cartItem.id,
            name = cartItem.name,
            price = cartItem.money.amount,
            currency = cartItem.money.currencySymbol,
            imageUrl = cartItem.imageUrl,
            quantity = cartItem.quantity
        )
    }
}
