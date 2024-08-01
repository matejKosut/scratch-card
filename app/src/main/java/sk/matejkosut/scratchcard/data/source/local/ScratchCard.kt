package sk.matejkosut.scratchcard.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "scratch_card"
)
data class ScratchCard(
    @PrimaryKey val code: Int,
    var state: Int // 1 - unscratched, 2 - scratched, 3 - activated
)