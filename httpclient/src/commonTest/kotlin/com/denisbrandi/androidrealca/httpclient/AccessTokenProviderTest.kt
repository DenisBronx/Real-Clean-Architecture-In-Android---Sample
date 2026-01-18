package com.denisbrandi.androidrealca.httpclient

import kotlin.test.*

class AccessTokenProviderTest {
    @Test
    fun `EXPECT accessToken header`() {
        val result = AccessTokenProvider.getAccessTokenHeader()

        assertEquals(
            "Authorization" to "Bearer sghe50o5rkn9n9n1azs0ectmxnjjls7r9i1tksz3",
            result
        )
    }
}
