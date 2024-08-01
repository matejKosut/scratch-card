package sk.matejkosut.scratchcard.data.source.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ActivationService {

    @GET("/version")
    suspend fun activateScratchCard(@Query("code") code: Int): ActivationResponse

}