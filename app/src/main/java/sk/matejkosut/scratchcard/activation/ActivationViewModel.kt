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
import sk.matejkosut.scratchcard.di.ApplicationScope
import sk.matejkosut.scratchcard.di.IoDispatcher
import sk.matejkosut.scratchcard.home.HomeUiState
import javax.inject.Inject

data class ActivationUiState(
    val state: Int = 0,
    val error: Int = 0
)

@HiltViewModel
class ActivationViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
    private val scratchCardRepository: ScratchCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivationUiState())
    val uiState: StateFlow<ActivationUiState> = _uiState

    fun activateCard() {
        _uiState.value = ActivationUiState(1, 0)
        scope.launch {
            withContext(dispatcher) {
                val active = scratchCardRepository.activateScratchCard(
                    scratchCardRepository.getScratchCardCode()
                )
                _uiState.value = ActivationUiState(active,  0)
            }
        }
    }

}