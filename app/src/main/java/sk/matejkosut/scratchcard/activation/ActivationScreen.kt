package sk.matejkosut.scratchcard.activation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.util.TableInfo
import sk.matejkosut.scratchcard.home.HomeViewModel

@Composable
fun ActivationScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column {
        Text(text = "Card activation")
    }
}