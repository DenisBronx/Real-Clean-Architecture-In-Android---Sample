package com.denisbrandi.androidrealca.cache

import kotlinx.serialization.KSerializer

interface CacheProvider {
    fun <T : Any> getCachedObject(
        fileName: String,
        serializer: KSerializer<T>,
        defaultValue: T
    ): CachedObject<T>
}
