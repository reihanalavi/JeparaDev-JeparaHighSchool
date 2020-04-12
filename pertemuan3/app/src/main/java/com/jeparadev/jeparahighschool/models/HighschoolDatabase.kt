package com.jeparadev.jeparahighschool.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Highschool::class],
    version = 1,
    exportSchema = true
)
abstract class HighschoolDatabase: RoomDatabase() {

    abstract fun highschoolDao(): HighschoolDao

    companion object {

        @Volatile private var instance: HighschoolDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            HighschoolDatabase::class.java,
            "highschooldb"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}