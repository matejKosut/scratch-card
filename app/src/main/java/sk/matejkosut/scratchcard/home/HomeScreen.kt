package sk.matejkosut.scratchcard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    onBack: () -> Unit,
    onScratch: () -> Unit,
    onActivation: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column {
        Text(text = viewModel.getScratchCardState())
        Button(onClick = onScratch) {
            Text(text = "Go to Card scratching")
        }
        Button(onClick = onActivation) {
            Text(text = "Go to Card activation")
        }
    }
}