package com.rumosoft.mvicounter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rumosoft.mvicounter.presentation.action.CounterAction
import com.rumosoft.mvicounter.presentation.action.CounterIntent
import com.rumosoft.mvicounter.presentation.model.CounterState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {
    companion object {
        const val MAX_ACTIONS = 999
    }

    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state

    private val actions = MutableSharedFlow<CounterAction>(extraBufferCapacity = MAX_ACTIONS)

    init {
        viewModelScope.launch {
            actions.collect { action ->
                _state.update { it.copy(loading = true) }
                val result = CounterProcessor().process(action)
                val newState = CounterReducer().reduce(_state.value, result)
                _state.value = newState
                _state.update { it.copy(loading = false) }
            }
        }
    }

    fun dispatch(intent: CounterIntent) {
        viewModelScope.launch {
            actions.emit(intent.toAction())
        }
    }
}
