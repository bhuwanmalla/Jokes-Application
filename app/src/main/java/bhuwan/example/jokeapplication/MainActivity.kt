package bhuwan.example.jokeapplication

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bhuwan.example.jokeapplication.ViewModel.JokeViewModel
import bhuwan.example.jokeapplication.ViewModel.ViewModelFactory
import bhuwan.example.jokeapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: JokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()


        observeData()

        binding.nextBtn.setOnClickListener {
            viewModel.fetchJoke()
        }
    }

    private fun observeData() {
        viewModel.joke.observe(this, Observer { jokes ->
            binding.result.text = jokes?.joke ?: "No joke available"
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                binding.errorMessage.text = errorMessage
            } else {
                binding.errorMessage.text = ""
            }
        })
    }
}
