package sk.matejkosut.scratchcard.activation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sk.matejkosut.scratchcard.data.ScratchCardRepository
import sk.matejkosut.scratchcard.data.source.network.NoNetworkException
import sk.matejkosut.scratchcard.di.ApplicationScope
import sk.matejkosut.scratchcard.di.IoDispatcher
import javax.inject.Inject

data class ActivationUiState(
    val state: Int = 0
)

@HiltViewModel
class ActivationViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
    private val scratchCardRepository: ScratchCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivationUiState())
    val uiState: StateFlow<ActivationUiState> = _uiState

    init {
        scope.launch {
            withContext(dispatcher) {
                val state = scratchCardRepository.getScratchCardState()
                // activation possible only when card is scratched
                if (state == 1) {
                    _uiState.value = ActivationUiState(-1)
                } else if (state == 3) {
                    _uiState.value = ActivationUiState(2)
                }
            }
        }
    }

    fun activateCard() {
        _uiState.value = ActivationUiState(1)
        scope.launch {
            withContext(dispatcher) {
                val code = scratchCardRepository.getScratchCardCode()
                try {
                    val active = scratchCardRepository.activateScratchCard(code)
                    scratchCardRepository.updateScratchCardState(3, code)
                    if (active)
                        _uiState.value = ActivationUiState(2)
                    else
                        _uiState.value = ActivationUiState(-3)
                } catch (e: NoNetworkException) {
                    _uiState.value = ActivationUiState(-2)
                }
            }
        }
    }

}