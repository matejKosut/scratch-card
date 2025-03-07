package sk.matejkosut.scratchcard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sk.matejkosut.scratchcard.R

@Composable
fun HomeScreen(
    onBack: () -> Unit,
    onScratch: () -> Unit,
    onActivation: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.RESUMED -> {
                viewModel.refresh()
            }
            else -> {}
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = uiState.state.asTextState())
        Button(onClick = onScratch) {
            Text(text = stringResource(id = R.string.home__goto_scratching))
        }
        Button(onClick = onActivation) {
            Text(text = stringResource(id = R.string.home__goto_activation))
        }
    }
}

@Composable
private fun Int.asTextState(): String {
    return when (this) {
        0 -> stringResource(id = R.string.home__loading)
        1 -> stringResource(id = R.string.home__unscratched)
        2 -> stringResource(id = R.string.home__scratched)
        3 -> stringResource(id = R.string.home__activated)
        else -> stringResource(id = R.string.home__none)
    }
}