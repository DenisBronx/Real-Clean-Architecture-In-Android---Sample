package com.denisbrandi.androidrealca.user.domain.model

import kotlin.test.*

class EmailTest {

    @Test
    fun `EXPECT invalid email WHEN no text entered`() {
        val result = Email("").isValid()

        assertFalse(result)
    }

    @Test
    fun `EXPECT invalid email WHEN blank text entered`() {
        val result = Email("  ").isValid()

        assertFalse(result)
    }

    @Test
    fun `EXPECT invalid email WHEN text entered`() {
        val result = Email("a").isValid()

        assertFalse(result)
    }

    @Test
    fun `EXPECT invalid email WHEN domain entered`() {
        val result = Email("test.com").isValid()

        assertFalse(result)
    }

    @Test
    fun `EXPECT invalid email WHEN at symbol entered`() {
        val result = Email("test@").isValid()

        assertFalse(result)
    }

    @Test
    fun `EXPECT valid email WHEN valid email entered`() {
        val result = Email("test@address.com").isValid()

        assertTrue(result)
    }
}
