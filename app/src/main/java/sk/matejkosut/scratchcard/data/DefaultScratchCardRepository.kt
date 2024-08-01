package sk.matejkosut.scratchcard.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sk.matejkosut.scratchcard.data.source.local.ScratchCard
import sk.matejkosut.scratchcard.data.source.local.ScratchCardDao
import sk.matejkosut.scratchcard.data.source.network.ActivationService
import sk.matejkosut.scratchcard.di.ApplicationScope
import sk.matejkosut.scratchcard.di.IoDispatcher
import javax.inject.Inject

class DefaultScratchCardRepository @Inject constructor(
    private val networkDataSource: ActivationService,
    private val localDataSource: ScratchCardDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope
): ScratchCardRepository {
    override fun createScratchCard(code: Int, state: Int) {
        // there is always only one entry in the database in this case,
        // db is recreated with the start of the application
        scope.launch {
            withContext(dispatcher) {
                localDataSource.deleteAll()
                localDataSource.createScratchCard(
                    ScratchCard(code, state)
                )
            }
        }
    }

    override fun getScratchCardState(): Int {
        // there is always only one entry in the database in this case,
        // db is recreated with the start of the application
        return localDataSource.getAll().first().state
    }

    override fun getScratchCardCode(): Int {
        // there is always only one entry in the database in this case,
        // db is recreated with the start of the application
        return localDataSource.getAll().first().code
    }

    override suspend fun activateScratchCard(code: Int): Int {
        return networkDataSource.activateScratchCard(code).android
    }
}