package sk.matejkosut.scratchcard.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sk.matejkosut.scratchcard.data.ScratchCardRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
    ) : ViewModel() {

    init {
        val code = (0..100).random()
        scratchCardRepository.createScratchCard(code, 1) // 1 - unscratched, 2 - scratched, 3 - activated
    }

    fun getScratchCardState(): String {
        val state = scratchCardRepository.getScratchCardState()
        return when (state) {
            1 -> "Unscratched"
            2 -> "Scratched"
            3 -> "Activated"
            else -> "None"
        }
    }

}