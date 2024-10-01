package com.denisbrandi.androidrealca.foundations

import kotlin.test.Test
import kotlin.test.assertEquals

class AnswerTest {
    @Test
    fun `EXPECT success callback on fold WHEN Answer is Success`() {
        val sut = Answer.Success("success")

        val actual = sut.fold(success = { it }, error = {})

        assertEquals("success", actual)
    }

    @Test
    fun `EXPECT error callback on fold WHEN Answer is Error`() {
        val sut = Answer.Error("failure")

        val actual = sut.fold(success = { }, error = { it })

        assertEquals("failure", actual)
    }
}
