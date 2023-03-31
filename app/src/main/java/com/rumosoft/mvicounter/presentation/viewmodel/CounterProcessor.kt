package com.rumosoft.mvicounter.presentation.viewmodel

import com.rumosoft.mvicounter.presentation.action.CounterAction
import com.rumosoft.mvicounter.presentation.action.CounterAction.Decrement
import com.rumosoft.mvicounter.presentation.action.CounterAction.Increment
import com.rumosoft.mvicounter.presentation.model.CounterResult
import com.rumosoft.mvicounter.presentation.model.CounterResult.DecrementResult
import com.rumosoft.mvicounter.presentation.model.CounterResult.IncrementResult
import kotlinx.coroutines.delay

class CounterProcessor {
    suspend fun process(intent: CounterAction): CounterResult {
        delay(2_000)
        return when (intent) {
            is Increment -> IncrementResult(1)
            is Decrement -> DecrementResult(-1)
        }
    }
}
