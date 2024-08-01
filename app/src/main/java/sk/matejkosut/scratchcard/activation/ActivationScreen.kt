package sk.matejkosut.scratchcard.activation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sk.matejkosut.scratchcard.home.HomeViewModel

@Composable
fun ActivationScreen(
    onBack: () -> Unit,
    viewModel: ActivationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = viewModel::activateCard) {
            Text(text = "Activate the Card")
        }
        Text(text = uiState.state.asTextState())
    }
}

private fun Int.asTextState(): String {
    return when (this) {
        0 -> "Not activated"
        1 -> "Activating..."
        in 277028..Integer.MAX_VALUE -> "Card is activated."
        else -> "Error"
    }
}