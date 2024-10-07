package com.denisbrandi.androidrealca.cache

import com.russhwolf.settings.MapSettings
import kotlin.test.*
import kotlinx.serialization.Serializable

class RealCachedObjectTest {

    private val sut = RealCachedObject(
        fileName = "fileName",
        settings = MapSettings(),
        serializer = TestModel.serializer(),
        defaultValue = TestModel("")
    )

    @Test
    fun `EXPECT default value WHEN nothing is stored`() {
        assertEquals(TestModel(""), sut.get())
    }

    @Test
    fun `EXPECT value stored`() {
        sut.put(TestModel("id"))

        assertEquals(TestModel("id"), sut.get())
    }

    @Serializable
    data class TestModel(val id: String)
}
