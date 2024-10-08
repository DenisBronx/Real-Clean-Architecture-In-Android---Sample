package com.denisbrandi.androidrealca.cache

import com.denisbrandi.androidrealca.cache.fixture.TestModel
import com.russhwolf.settings.MapSettings
import kotlin.test.*

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
}
