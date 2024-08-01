package sk.matejkosut.scratchcard.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScratchCardDao {

    @Query("SELECT * FROM scratch_card")
    fun getAll(): List<ScratchCard>

    @Insert
    fun createScratchCard(scratchCard: ScratchCard)

    @Query("DELETE FROM scratch_card")
    suspend fun deleteAll()

}