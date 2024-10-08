package com.denisbrandi.androidrealca.cache

import kotlinx.coroutines.flow.Flow

interface FlowCachedObject<T: Any>: CachedObject<T> {
    fun observe(): Flow<T>
}
