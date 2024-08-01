package sk.matejkosut.scratchcard.scratch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import sk.matejkosut.scratchcard.home.HomeViewModel

@Composable
fun ScratchScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column {
        Text(text = "Card scratching")
    }
}