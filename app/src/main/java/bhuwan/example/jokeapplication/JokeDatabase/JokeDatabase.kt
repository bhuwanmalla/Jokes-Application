package bhuwan.example.jokeapplication.JokeDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bhuwan.example.jokeapplication.DAO.JokeDao
import bhuwan.example.jokeapplication.JokeEntity.JokeEntity

@Database(entities = [JokeEntity::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {

    abstract fun getJokes(): JokeDao

    companion object {
        @Volatile
        private var INSTANCE: JokeDatabase? = null

        fun getDatabase(context: Context): JokeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JokeDatabase::class.java, "joke_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}