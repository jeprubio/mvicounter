package com.rumosoft.mvicounter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rumosoft.mvicounter.presentation.action.CounterAction
import com.rumosoft.mvicounter.presentation.action.CounterIntent
import com.rumosoft.mvicounter.presentation.action.toAction
import com.rumosoft.mvicounter.presentation.model.CounterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state

    private val _intent = MutableSharedFlow<CounterAction>()
    private val intent: Flow<CounterAction> = _intent.asSharedFlow()

    init {
        viewModelScope.launch {
            intent.collect { action ->
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
            _intent.emit(intent.toAction())
        }
    }
}
