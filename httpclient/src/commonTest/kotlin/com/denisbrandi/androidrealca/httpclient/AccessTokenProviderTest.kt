package com.denisbrandi.androidrealca.httpclient

import kotlin.test.*

class AccessTokenProviderTest {
    @Test
    fun `EXPECT accessToken header`() {
        val result = AccessTokenProvider.getAccessTokenHeader()

        assertEquals(
            "Authorization" to "Bearer t98jaw2i9048ctpaxb19g901p3yaasy5xyvdwue6",
            result
        )
    }
}
