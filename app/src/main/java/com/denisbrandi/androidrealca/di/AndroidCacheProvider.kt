package com.denisbrandi.androidrealca.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.denisbrandi.androidrealca.cache.*
import com.russhwolf.settings.SharedPreferencesSettings
import kotlinx.serialization.KSerializer

class AndroidCacheProvider(
    private val applicationContext: Context
) : CacheProvider {

    private val settings by lazy {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        SharedPreferencesSettings(sharedPrefs)
    }

    override fun <T : Any> getCachedObject(
        fileName: String,
        serializer: KSerializer<T>,
        defaultValue: T
    ): CachedObject<T> {
        return RealCachedObject(fileName, settings, serializer, defaultValue)
    }

    override fun <T : Any> getFlowCachedObject(
        fileName: String,
        serializer: KSerializer<T>,
        defaultValue: T
    ): FlowCachedObject<T> {
        return RealFlowCachedObject(getCachedObject(fileName, serializer, defaultValue))
    }
}
