package com.rumosoft.mvicounter.presentation.viewmodel

import com.rumosoft.mvicounter.presentation.model.CounterResult
import com.rumosoft.mvicounter.presentation.model.CounterResult.DecrementResult
import com.rumosoft.mvicounter.presentation.model.CounterResult.IncrementResult
import com.rumosoft.mvicounter.presentation.model.CounterState

internal fun reduce(action: CounterResult, state: CounterState): CounterState =
    when (action) {
        is IncrementResult -> state.copy(counter = state.counter + 1)
        is DecrementResult -> state.copy(counter = state.counter - 1)
    }
