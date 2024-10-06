package com.denisbrandi.androidrealca.user.domain.model

import kotlin.test.*

class PasswordTest {

    @Test
    fun `EXPECT valid password WHEN password has at least 8 characters`() {
        val result = Password("abcdefgh").isValid()

        assertTrue(result)
    }

    @Test
    fun `EXPECT invalid password WHEN password has less than 8 characters`() {
        val result = Password("abcdefg").isValid()

        assertFalse(result)
    }
}
