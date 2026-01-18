package com.denisbrandi.androidrealca.httpclient

import io.ktor.client.engine.mock.*
import kotlin.test.*

class RealHttpClientProviderTest {

    private val sut = RealHttpClientProvider
    private val mockEngine = MockEngine(
        MockEngineConfig().apply {
            addHandler { TODO() }
        }
    )

    @Test
    fun `EXPECT only 1 client returned`() {
        sut.createClient(mockEngine)

        val client1 = sut.getClient()
        val client2 = sut.getClient()

        assertSame(client1, client2)
    }
}
