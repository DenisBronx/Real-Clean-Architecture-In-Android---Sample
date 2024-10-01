package com.denisbrandi.androidrealca.foundations

sealed class Answer<out T, out E> {
    data class Success<out T>(
        val data: T,
    ) : Answer<T, Nothing>()

    data class Error<out E>(
        val reason: E,
    ) : Answer<Nothing, E>()

    inline fun <C> fold(
        success: (T) -> C,
        error: (E) -> C,
    ): C =
        when (this) {
            is Success -> success(data)
            is Error -> error(reason)
        }
}
