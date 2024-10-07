@file:OptIn(ExperimentalSettingsApi::class, ExperimentalSerializationApi::class)

package com.denisbrandi.androidrealca.cache

import com.russhwolf.settings.*
import com.russhwolf.settings.serialization.*
import kotlinx.serialization.*

class RealCachedObject<T : Any>(
    private val fileName: String,
    private val settings: Settings,
    private val serializer: KSerializer<T>,
    private val defaultValue: T
) : CachedObject<T> {
    override fun put(value: T) {
        settings.encodeValue(serializer, fileName, value)
    }

    override fun get(): T {
        return settings.decodeValue(serializer, fileName, defaultValue)
    }
}
