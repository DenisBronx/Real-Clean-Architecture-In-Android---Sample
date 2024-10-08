package com.denisbrandi.androidrealca.viewmodel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

interface StateViewModel<State> {
    val state: StateFlow<State>
}

interface EventViewModel<ViewEvent> {
    val viewEvent: Flow<ViewEvent>
}

class StateDelegate<State> : StateViewModel<State> {
    private lateinit var _state: MutableStateFlow<State>
    override val state: StateFlow<State>
        get() {
            return _state.asStateFlow()
        }

    fun setDefaultState(state: State) {
        _state = MutableStateFlow(state)
    }

    fun updateState(block: (State) -> State) {
        _state.update {
            block(it)
        }
    }

    inline fun <reified SubState : State> onState(block: (SubState) -> Unit) {
        val currentState = state.value
        if (currentState is SubState) {
            block(currentState)
        }
    }
}

class EventDelegate<ViewEvent> : EventViewModel<ViewEvent> {
    private val _viewEvent = MutableSharedFlow<ViewEvent>()
    override val viewEvent: Flow<ViewEvent> = _viewEvent.asSharedFlow()

    fun sendEvent(scope: CoroutineScope, newEvent: ViewEvent) {
        scope.launch {
            sendEvent(newEvent)
        }
    }

    suspend fun sendEvent(newEvent: ViewEvent) {
        _viewEvent.emit(newEvent)
    }
}