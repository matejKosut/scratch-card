package sk.matejkosut.scratchcard.data

import sk.matejkosut.scratchcard.data.source.local.ScratchCard
import sk.matejkosut.scratchcard.data.source.local.ScratchCardDao

class FakeLocalDataSource(private var scratchCard: ScratchCard?): ScratchCardDao {
    override fun getAll(): List<ScratchCard> {
        scratchCard?.let {
            return listOf(it)
        }
        return emptyList()
    }

    override fun createScratchCard(scratchCard: ScratchCard) {
        this.scratchCard = scratchCard
    }

    override suspend fun updateState(state: Int, code: Int) {
        scratchCard?.state = state
    }

    override suspend fun deleteAll() {
        scratchCard = null
    }
}