package sk.matejkosut.scratchcard.data

import sk.matejkosut.scratchcard.data.source.network.ActivationResponse
import sk.matejkosut.scratchcard.data.source.network.ActivationService

class FakeNetworkDataSource(): ActivationService {

    private val android1 = "277028"
    private val android2 = "287028"
    private val android3 = "267028"
    private val android4 = "-1"

    override suspend fun activateScratchCard(code: Int): ActivationResponse {
        return when (code) {
            CODE1 -> ActivationResponse(android1)
            CODE2 -> ActivationResponse(android2)
            CODE3 -> ActivationResponse(android3)
            else -> ActivationResponse(android4)
        }
    }

    companion object {
        const val CODE1 = 1
        const val CODE2 = 2
        const val CODE3 = 3
    }
}