# Jokes Application

JokesApplication is a simple Android application designed to fetch and display jokes. It incorporates the Model-View-ViewModel (MVVM) architectural pattern, utilizes Hilt-Dagger for dependency injection, and offers offline functionality by caching jokes in a local database.

## App's Interface

The following images showcase the Home Screen and Quote Display Screen of the app:


<p align="center">
  <img src="https://github.com/bhuwanmalla/Jokes-Application/blob/main/Joke.png" alt="Home Screen" width="45%" style="margin-right: 10px;">
  <img src="https://github.com/bhuwanmalla/Jokes-Application/blob/main/DatafromDB.png" alt="Quote Display Screen" width="45%">
</p>

## Features

- Fetch Jokes from API: Connects to a jokes API to retrieve a variety of jokes and displays them to the user.
- Offline Access: Caches jokes in a local database so that users can access them even when offline.
- MVVM Architecture: Utilizes the MVVM pattern to ensure a clean separation between UI and business logic.
- Hilt-Dagger Dependency Injection: Employs Hilt-Dagger for dependency injection, promoting modularity and testability.

## Tech Stack

- Language: Kotlin
- Architecture: MVVM (Model-View-ViewModel)
- Dependency Injection: Hilt-Dagger
- Networking: Retrofit (for API calls)
- Local Storage: Room (for offline caching)
