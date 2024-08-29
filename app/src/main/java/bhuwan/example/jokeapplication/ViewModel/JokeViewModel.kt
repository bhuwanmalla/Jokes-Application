package bhuwan.example.jokeapplication.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bhuwan.example.jokeapplication.Joke.Joke
import bhuwan.example.jokeapplication.JokeEntity.JokeEntity
import bhuwan.example.jokeapplication.NetworkUtil.NetworkUtil
import bhuwan.example.jokeapplication.Repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repository: JokeRepository,
    private val networkUtil: NetworkUtil
) : ViewModel() {

    private val _joke = MutableLiveData<Joke?>()
    val joke: LiveData<Joke?>
        get() = _joke

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    private val _jokeList = MutableLiveData<List<JokeEntity>>(emptyList())

    private var jokeIndex: Int = 0

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            _isLoading.value = true
            if (networkUtil.isNetworkAvailable()) {
                try {
                    val response = repository.fetchJoke()
                    if (response.isSuccessful) {
                        val fetchedJoke = response.body()
                        if (fetchedJoke != null) {
                            _joke.value = fetchedJoke
                            repository.insertJoke(fetchedJoke)
                            _errorMessage.value = null
                        } else {
                            _errorMessage.value = "No jokes found."
                        }
                    } else {
                        _errorMessage.value = "Failed to fetch joke"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = e.message
                } finally {
                    _isLoading.value = false
                }
            } else {
                fetchFromLocal()
            }
        }
    }

    private fun fetchFromLocal() {
        viewModelScope.launch {
            try {
                val jokes = repository.getAllJokes()
                if (jokes.isNotEmpty()) {
                    _jokeList.value = jokes
                    if (jokeIndex < jokes.size) {
                        val jokeEntity = jokes[jokeIndex]
                        _joke.value = Joke(
                            id = jokeEntity.id.toString(),
                            joke = jokeEntity.joke,
                            status = jokeEntity.status
                        )
                        jokeIndex++
                    } else {
                        _errorMessage.value = "This is the only data you have in your database."
                    }
                } else {
                    _errorMessage.value = "Database is empty"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
