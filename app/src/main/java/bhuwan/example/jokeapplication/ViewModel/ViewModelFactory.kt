package bhuwan.example.jokeapplication.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bhuwan.example.jokeapplication.NetworkUtil.NetworkUtil
import bhuwan.example.jokeapplication.Repository.JokeRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: JokeRepository,
    private val networkUtil: NetworkUtil
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JokeViewModel(repository, networkUtil) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}