@file:OptIn(ExperimentalCoroutinesApi::class)

package com.denisbrandi.androidrealca.coroutines.testdispatcher

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainCoroutineRule : TestWatcher() {

    private val testDispatcher = UnconfinedTestDispatcher()

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}
