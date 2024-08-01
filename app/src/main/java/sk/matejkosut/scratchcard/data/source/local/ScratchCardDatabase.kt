package sk.matejkosut.scratchcard.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScratchCard::class], version = 1, exportSchema = false)
abstract class ScratchCardDatabase : RoomDatabase() {

    abstract fun scratchCardDao(): ScratchCardDao
}