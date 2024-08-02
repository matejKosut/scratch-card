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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sk.matejkosut.scratchcard.R

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
            Text(text = stringResource(id = R.string.activation__activate))
        }
        Text(text = uiState.state.asTextState())
    }
}

@Composable
private fun Int.asTextState(): String {
    return when (this) {
        -1 -> stringResource(id = R.string.activation__not_possible)
        -2 -> stringResource(id = R.string.activation__no_internet)
        0 -> stringResource(id = R.string.activation__not_activated)
        1 -> stringResource(id = R.string.activation__activating)
        2 -> stringResource(id = R.string.activation__activated)
        else -> stringResource(id = R.string.activation__error)
    }
}

private fun Int.asEnabled(): Boolean {
    return this != -1 && this != 2
}