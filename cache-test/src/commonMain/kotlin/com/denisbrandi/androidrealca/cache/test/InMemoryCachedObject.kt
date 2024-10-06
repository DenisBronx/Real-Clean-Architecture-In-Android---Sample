package com.denisbrandi.androidrealca.cache.test

import com.denisbrandi.androidrealca.cache.CachedObject

class InMemoryCachedObject<T : Any>(
    defaultValue: T
) : CachedObject<T> {

    private var cachedValue = defaultValue

    override fun put(value: T) {
        cachedValue = value
    }

    override fun get(): T {
        return cachedValue
    }
}
