# FootballEventApplication
This is a sample Football Event Application, This will retrieve and show avalabile Team list, Match list and each specific team details using API calls.

## IDE And Architecture

* [Adroid Studio][11] Version: Android Studio Electric Eel | 2022.1.1
* MVVM Clean Archiecture: Application Archiecture.
* [ViewModel][10]: Composable state management.

[10]: https://developer.android.com/topic/libraries/architecture/viewmodel
[11]: https://developer.android.com/studio

## Libraries Used :books:

* [Compose][0]: Toolkit for building native UI (in a declarative way - 100% Kotlin).
* [Coroutines][1]: Library support for Kotlin coroutines.
* [Flows][2]: Stream processing API, built on top of Coroutines.
* [Compose Navigation][3]: for tabs navigation using Jetpack Compose.
* [Dagger Hilt][4]: Dependency injection library for Android.
* [Retrofit][5]: Type-safe REST client for Android to consume RESTful web services.
* [Espresso][6]: Android UI Testing framework.
* [MockWebServer][7]: A scriptable web server for testing HTTP clients, used for Instrumentation tests in this project.
* [Glide][8]: Image downloading and caching library supported by Jetpack Compose.
* [Exoplayer][9] : Video player and caching library supported by Jetpack Compose and developet by Google.

[0]:  https://developer.android.com/jetpack
[1]:  https://github.com/Kotlin/kotlinx.coroutines
[2]:  https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/
[3]:  https://developer.android.com/jetpack/compose/navigation
[4]:  https://dagger.dev/hilt/
[5]:  https://github.com/square/retrofit
[6]:  https://developer.android.com/training/testing/espresso/
[7]:  https://github.com/square/okhttp/tree/master/mockwebserver
[8]: https://github.com/bumptech/glide
[9]: https://github.com/google/ExoPlayer

## Package Structure :bookmark_tabs:

* core - Package that contains files that can be accessible by another package from the project. 
* data - Data(Local or Remote) Package that can only access the domain package.
* di - DI package for preform dependecy injection.
* domain - The innermost layer. That can access other layers.
* presentation - UI package that can only access the domain package.


# App Preview :octocat:

https://user-images.githubusercontent.com/11756630/222366221-f48522f8-59e3-4008-8566-2c37262afb04.mp4

# App Architecture Diagram

![architecture](https://user-images.githubusercontent.com/11756630/222377667-406cd12e-be08-4b81-a0e1-5dc92c292cfd.png)


