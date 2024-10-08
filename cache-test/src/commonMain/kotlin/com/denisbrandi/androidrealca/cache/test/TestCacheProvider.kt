package com.denisbrandi.androidrealca.cache.test

import com.denisbrandi.androidrealca.cache.*
import kotlinx.serialization.KSerializer

class TestCacheProvider(
    private val expectedFileName: String,
    private val expectedDefaultValue: Any
) : CacheProvider {

    lateinit var providedCachedObject: InMemoryCachedObject<*>

    override fun <T : Any> getCachedObject(
        fileName: String,
        serializer: KSerializer<T>,
        defaultValue: T
    ): CachedObject<T> {
        return if (expectedFileName == fileName && expectedDefaultValue == defaultValue) {
            InMemoryCachedObject(defaultValue).also {
                providedCachedObject = it
            }
        } else {
            throw IllegalStateException("getCachedObject not stubbed")
        }
    }
}