package com.denisbrandi.androidrealca.httpclient

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object RealHttpClientProvider : HttpClientProvider {

    private val httpClient by lazy {
        createClient(CIO.create())
    }

    fun createClient(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    override fun getClient(): HttpClient {
        return httpClient
    }
}
