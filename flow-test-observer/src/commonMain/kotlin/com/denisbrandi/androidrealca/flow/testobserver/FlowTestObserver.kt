@file:OptIn(ExperimentalCoroutinesApi::class)

package com.denisbrandi.androidrealca.flow.testobserver

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class FlowTestObserver<T : Any>(
    private val flow: Flow<T>,
    coroutineScope: CoroutineScope
) {
    private val emittedValues = mutableListOf<T>()
    private val job: Job = flow.onEach {
        emittedValues.add(it)
    }.launchIn(coroutineScope)

    fun getValues() = emittedValues

    fun stopObserving() {
        job.cancel()
    }

    fun getFlow() = flow
}

fun <T : Any> Flow<T>.test(coroutineScope: CoroutineScope = CoroutineScope(UnconfinedTestDispatcher())) =
    FlowTestObserver(this, coroutineScope)
