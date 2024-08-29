package bhuwan.example.jokeapplication.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bhuwan.example.jokeapplication.JokeEntity.JokeEntity

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(joke: JokeEntity)

    @Query("SELECT * FROM jokes ORDER BY id Desc")
    suspend fun getJokesInDesc() : List<JokeEntity>

}