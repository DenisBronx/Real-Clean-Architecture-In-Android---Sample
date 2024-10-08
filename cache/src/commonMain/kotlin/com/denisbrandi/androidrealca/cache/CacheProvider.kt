package com.denisbrandi.androidrealca.cache

import kotlinx.serialization.KSerializer

interface CacheProvider {
    fun <T : Any> getCachedObject(
        fileName: String,
        serializer: KSerializer<T>,
        defaultValue: T
    ): CachedObject<T>

    fun <T : Any> getFlowCachedObject(
        fileName: String,
        serializer: KSerializer<T>,
        defaultValue: T
    ): FlowCachedObject<T>
}
