package sk.matejkosut.scratchcard.scratch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sk.matejkosut.scratchcard.data.ScratchCardRepository
import sk.matejkosut.scratchcard.di.ApplicationScope
import sk.matejkosut.scratchcard.di.DefaultDispatcher
import javax.inject.Inject

data class ScratchUiState(
    var state: Int = 0
)

@HiltViewModel
class ScratchViewModel @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
    private val scratchCardRepository: ScratchCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScratchUiState())
    val uiState: StateFlow<ScratchUiState> = _uiState

    init {
        scope.launch {
            withContext(dispatcher) {
                val state = scratchCardRepository.getScratchCardState()
                if (state == 2 || state == 3) {
                    val code = scratchCardRepository.getScratchCardCode()
                    _uiState.value = ScratchUiState(code)
                }
            }
        }
    }

    fun scratchCard() {
        _uiState.value = ScratchUiState(1)
        scope.launch {
            withContext(dispatcher) {
                revealCard()
                val code = scratchCardRepository.getScratchCardCode()
                scratchCardRepository.updateScratchCardState(2, code)
                _uiState.value = ScratchUiState(code)
            }
        }
    }

    private suspend fun revealCard() = delay(HEAVY_OPERATION_IN_MILLIS)
}

private const val HEAVY_OPERATION_IN_MILLIS = 2000L