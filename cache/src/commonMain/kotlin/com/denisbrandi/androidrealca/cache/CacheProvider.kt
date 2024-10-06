package com.denisbrandi.androidrealca.cache

interface CacheProvider {
    fun <T : Any> getCachedObject(fileName: String, defaultValue: T): CachedObject<T>
}
