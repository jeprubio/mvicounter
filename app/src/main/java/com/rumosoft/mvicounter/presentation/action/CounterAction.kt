package com.rumosoft.mvicounter.presentation.action

sealed interface CounterAction {
    object Increment : CounterAction
    object Decrement : CounterAction
}

sealed interface CounterIntent {
    object Increment : CounterIntent
    object Decrement : CounterIntent
}

fun CounterIntent.toAction(): CounterAction {
    return when (this) {
        CounterIntent.Increment -> CounterAction.Increment
        CounterIntent.Decrement -> CounterAction.Decrement
    }
}
