package bhuwan.example.jokeapplication.Repository

import bhuwan.example.jokeapplication.Api.DataService
import bhuwan.example.jokeapplication.DAO.JokeDao
import bhuwan.example.jokeapplication.Joke.Joke
import bhuwan.example.jokeapplication.JokeEntity.JokeEntity
import retrofit2.Response
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private val jokeDao: JokeDao,
    private val dataService: DataService
) {

    suspend fun fetchJoke(): Response<Joke> {
        return dataService.getJoke()
    }

    suspend fun insertJoke(joke: Joke) {
        jokeDao.insert(JokeEntity(0, joke.joke, joke.status))
    }

    suspend fun getAllJokes(): List<JokeEntity> {
        return jokeDao.getJokesInDesc()
    }
}