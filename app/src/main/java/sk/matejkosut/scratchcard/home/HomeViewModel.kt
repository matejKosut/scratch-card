package sk.matejkosut.scratchcard.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sk.matejkosut.scratchcard.data.ScratchCardRepository
import sk.matejkosut.scratchcard.di.ApplicationScope
import sk.matejkosut.scratchcard.di.IoDispatcher
import javax.inject.Inject

data class HomeUiState(
    val state: Int = 0,
    val error: Int = 0
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
    private val scratchCardRepository: ScratchCardRepository
    ) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        _uiState.value = HomeUiState(1, 0)
        scope.launch {
            withContext(dispatcher) {
                val state = scratchCardRepository.getScratchCardState()
                _uiState.value = HomeUiState(state,  0)
            }
        }
    }

}