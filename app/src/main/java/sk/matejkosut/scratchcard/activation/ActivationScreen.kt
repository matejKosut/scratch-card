package sk.matejkosut.scratchcard.activation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = viewModel::activateCard,
            enabled = uiState.state.asEnabled()
        ) {
            Text(text = "Activate the Card")
        }
        Text(text = uiState.state.asTextState())
    }
}

private fun Int.asTextState(): String {
    return when (this) {
        -1 -> "Not possible to activate. Card needs to be scratched first."
        -2 -> "No internet connection."
        0 -> "Not activated"
        1 -> "Activating..."
        2 -> "Card is activated."
        else -> "Error"
    }
}

private fun Int.asEnabled(): Boolean {
    return this != -1 && this != 2
}