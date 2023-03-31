package com.rumosoft.mvicounter.presentation.viewmodel

import com.rumosoft.mvicounter.presentation.model.CounterResult
import com.rumosoft.mvicounter.presentation.model.CounterState

class CounterReducer {
    fun reduce(state: CounterState, result: CounterResult): CounterState {
        return when (result) {
            is CounterResult.IncrementResult -> state.copy(counter = state.counter + result.result)
            is CounterResult.DecrementResult -> state.copy(counter = state.counter + result.result)
        }
    }
}
