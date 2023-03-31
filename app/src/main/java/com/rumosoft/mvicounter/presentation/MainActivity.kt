package com.rumosoft.mvicounter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rumosoft.mvicounter.presentation.action.CounterIntent.Decrement
import com.rumosoft.mvicounter.presentation.action.CounterIntent.Increment
import com.rumosoft.mvicounter.presentation.model.CounterState
import com.rumosoft.mvicounter.presentation.theme.MviCounterTheme
import com.rumosoft.mvicounter.presentation.viewmodel.CounterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviCounterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MviCounterScreen()
                }
            }
        }
    }
}

@Composable
fun MviCounterScreen(
    viewModel: CounterViewModel = viewModel(),
) {
    val uiState by viewModel.state.collectAsState()
    MviCounter(
        uiState = uiState,
        doOnIncrement = { viewModel.dispatch(Increment) },
        doOnDecrement = { viewModel.dispatch(Decrement) },
    )
}

@Composable
fun MviCounter(
    uiState: CounterState,
    doOnIncrement: () -> Unit,
    doOnDecrement: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),

    ) {
        Text("Counter: ${uiState.counter}")
        Text("Loading: ${uiState.loading}")

        Button(onClick = doOnIncrement) {
            Text("Increment")
        }

        Button(onClick = doOnDecrement) {
            Text("Decrement")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MviCounterTheme {
        MviCounter(
            CounterState(counter = 2),
            doOnIncrement = {},
            doOnDecrement = {},
        )
    }
}
