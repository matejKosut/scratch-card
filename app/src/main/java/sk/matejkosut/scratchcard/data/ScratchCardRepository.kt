package sk.matejkosut.scratchcard.data

import kotlinx.coroutines.flow.Flow
import sk.matejkosut.scratchcard.data.source.local.ScratchCard

interface ScratchCardRepository {

    fun createScratchCard(code: Int, state: Int)

    fun getScratchCardState(): Int

    fun getScratchCardCode(): Int

    suspend fun updateScratchCardState(state: Int, code: Int)

    suspend fun activateScratchCard(code: Int): String

}