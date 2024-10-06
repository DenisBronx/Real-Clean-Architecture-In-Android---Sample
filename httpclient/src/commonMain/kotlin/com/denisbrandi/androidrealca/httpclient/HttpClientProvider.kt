package com.denisbrandi.androidrealca.httpclient

import io.ktor.client.HttpClient

interface HttpClientProvider {
    fun getClient(): HttpClient
}
