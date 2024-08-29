package bhuwan.example.jokeapplication.Module

import android.app.Application
import androidx.room.Room
import bhuwan.example.jokeapplication.Api.DataService
import bhuwan.example.jokeapplication.DAO.JokeDao
import bhuwan.example.jokeapplication.JokeDatabase.JokeDatabase
import bhuwan.example.jokeapplication.NetworkUtil.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): JokeDatabase {
        return Room.databaseBuilder(application, JokeDatabase::class.java, "joke_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideJokeDao(database: JokeDatabase): JokeDao {
        return database.getJokes()
    }


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://icanhazdadjoke.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): DataService {
        return retrofit.create(DataService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkUtil(application: Application): NetworkUtil {
        return NetworkUtil(application.applicationContext)
    }
}
