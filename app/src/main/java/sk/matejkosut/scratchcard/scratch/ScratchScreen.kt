package sk.matejkosut.scratchcard.scratch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ScratchScreen(
    onBack: () -> Unit,
    viewModel: ScratchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            when(event){
                Lifecycle.Event.ON_STOP,Lifecycle.Event.ON_DESTROY  -> {
                    viewModel.cancelScratching()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = viewModel::scratchCard,
            enabled = uiState.code.asEnabled()
        ) {
            Text(text = "Scratch the Card")
        }
        Text(text = uiState.code.asTextState())
    }
}

private fun Int.asTextState(): String {
    return when (this) {
        0 -> "Scratching..."
        1 -> "Unscratched"
        else -> "Card is scratched, the code is $this."
    }
}

private fun Int.asEnabled(): Boolean {
    return this == 1
}