package sk.matejkosut.scratchcard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    onBack: () -> Unit,
    onScratch: () -> Unit,
    onActivation: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = uiState.state.asTextState())
        Button(onClick = onScratch) {
            Text(text = "Go to Card scratching")
        }
        Button(onClick = onActivation) {
            Text(text = "Go to Card activation")
        }
    }
}

private fun Int.asTextState(): String {
    return when (this) {
        1 -> "Unscratched"
        2 -> "Scratched"
        3 -> "Activated"
        else -> "None"
    }
}