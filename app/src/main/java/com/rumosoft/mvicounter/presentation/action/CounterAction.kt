package com.rumosoft.mvicounter.presentation.action

sealed interface CounterAction {
    object Increment : CounterAction
    object Decrement : CounterAction
}

sealed interface CounterIntent {
    fun toAction(): CounterAction
    object Increment : CounterIntent {
        override fun toAction(): CounterAction = CounterAction.Increment
    }

    object Decrement : CounterIntent {
        override fun toAction(): CounterAction = CounterAction.Decrement
    }
}
