package com.denisbrandi.androidrealca.cache

import com.denisbrandi.androidrealca.cache.fixture.TestModel
import com.denisbrandi.androidrealca.flow.testobserver.test
import com.russhwolf.settings.MapSettings
import kotlin.test.*

class RealFlowCachedObjectTest {

    private val cachedObject = RealCachedObject(
        fileName = "fileName",
        settings = MapSettings(),
        serializer = TestModel.serializer(),
        defaultValue = TestModel("")
    )
    private val sut = RealFlowCachedObject(cachedObject)

    @Test
    fun `EXPECT default value WHEN nothing is stored`() {
        assertEquals(TestModel(""), sut.get())
    }

    @Test
    fun `EXPECT value stored`() {
        sut.put(TestModel("id"))

        assertEquals(TestModel("id"), sut.get())
    }

    @Test
    fun `EXPECT default value WHEN observing and nothing is stored`() {
        val testObserver = sut.observe().test()

        assertEquals(listOf(TestModel("")), testObserver.getValues())
    }

    @Test
    fun `EXPECT cache updated WHEN observing and updating the cache`() {
        val testObserver = sut.observe().test()

        sut.put(TestModel("updated"))

        assertEquals(listOf(TestModel(""), TestModel("updated")), testObserver.getValues())
    }
}
