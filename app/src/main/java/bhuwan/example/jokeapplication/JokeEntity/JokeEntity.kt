package bhuwan.example.jokeapplication.JokeEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jokes")
data class JokeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val joke: String,
    val status: Int
)
