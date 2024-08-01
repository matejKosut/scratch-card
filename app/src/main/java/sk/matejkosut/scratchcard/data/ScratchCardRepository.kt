package sk.matejkosut.scratchcard.data

import sk.matejkosut.scratchcard.data.source.local.ScratchCard

interface ScratchCardRepository {

    fun createScratchCard(code: Int, state: Int)

    fun getScratchCardState(): Int

    fun getScratchCardCode(): Int

    suspend fun activateScratchCard(code: Int): Int

}