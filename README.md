# FootballEventApplication
This is a sample Football Event Application, This will retrieve and show avalabile Team list, Match list and each specific team details using API calls.

## Technologies

* Android Studio Electric Eel | 2022.1.1: Android Studio Version
* MVVM Clean Archiecture: Used Application Archiecture.
* ViewModel: Composable state management.
* Compose: Toolkit for building native UI (in a declarative way - 100% Kotlin).
* Coroutines: Library support for Kotlin coroutines.
* Flows: Stream processing API, built on top of Coroutines.
* Compose Navigation: for tabs navigation using Jetpack Compose.
* Dagger Hilt: Dependency injection library for Android.
* Retrofit: Type-safe REST client for Android to consume RESTful web services.
* Espresso: Android UI Testing framework.
* MockWebServer: A scriptable web server for testing HTTP clients, used for Instrumentation tests in this project.
* Glide: Image downloading and caching library supported by Jetpack Compose.
* Exoplayer : Video player and caching library supported by Jetpack Compose and developet by Google.

## Modules

* core - Module that contain files that can be accessible by other module from the project. 
* data - Android module that can only access domain module.
* domain - Kotlin module that cannot access any other module.
* presentation - UI module that can only access by domain module.
* di - DI module for preform dependecy the injection.


# App Preview

https://user-images.githubusercontent.com/11756630/222366221-f48522f8-59e3-4008-8566-2c37262afb04.mp4

# App Architecture Diagram

![architecture](https://user-images.githubusercontent.com/11756630/222377667-406cd12e-be08-4b81-a0e1-5dc92c292cfd.png)


