package bhuwan.example.jokeapplication.Api

import bhuwan.example.jokeapplication.Joke.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface DataService {

    @Headers("Accept: application/json")
    @GET("/")
    suspend fun getJoke(): Response<Joke>

}