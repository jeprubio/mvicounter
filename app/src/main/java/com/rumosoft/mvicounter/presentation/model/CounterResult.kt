package com.rumosoft.mvicounter.presentation.model

sealed interface CounterResult {
    data class IncrementResult(val result: Int) : CounterResult
    data class DecrementResult(val result: Int) : CounterResult
}
