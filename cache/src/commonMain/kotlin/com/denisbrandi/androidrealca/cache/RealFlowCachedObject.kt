package com.denisbrandi.androidrealca.cache

import kotlinx.coroutines.flow.*

class RealFlowCachedObject<T : Any>(
    private val cachedObject: CachedObject<T>
) : FlowCachedObject<T>, CachedObject<T> by cachedObject {

    private val cacheFlow = MutableStateFlow(get())

    override fun put(value: T) {
        cachedObject.put(value)
        cacheFlow.value = value
    }

    override fun observe(): Flow<T> {
        return cacheFlow.asStateFlow()
    }
}
