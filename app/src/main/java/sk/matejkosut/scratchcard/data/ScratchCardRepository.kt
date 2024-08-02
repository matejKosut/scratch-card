package sk.matejkosut.scratchcard.data

interface ScratchCardRepository {

    fun createScratchCard(code: Int, state: Int)

    fun getScratchCardState(): Int

    fun getScratchCardCode(): Int

    suspend fun updateScratchCardState(state: Int, code: Int)

    suspend fun activateScratchCard(code: Int): Boolean

}