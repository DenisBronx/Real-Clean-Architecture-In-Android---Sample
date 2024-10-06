package com.denisbrandi.androidrealca.cache

interface CachedObject<T : Any> {
    fun put(value: T)
    fun get(): T
}
