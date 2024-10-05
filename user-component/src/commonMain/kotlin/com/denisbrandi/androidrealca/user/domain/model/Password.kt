package com.denisbrandi.androidrealca.user.domain.model

class Password(private val value: String) {

    fun isValid(): Boolean {
        return value.length >= 8
    }
}
