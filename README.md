# Game of Thrones for Android Challenge
Android developers face challenges almost everyday during development: performance, security, backwards compatibility, testing... And mainly refactoring for it's own or legacy code. 
This repository contains a project to face an small challenge where the developer should add some new features, detect (and implement) patterns, add tests, re-think the architecture and do a clean code.

Game of Thrones for Android Challenge offers an app using an API to get data for [Game of Thrones][GameOfThronesLink] tv show. It's ready to run, it's working, but the code need to be improved. That's your challenge!

## Requirements

- Android Studio
- Android API 21~28

## Local Development 

- ./gradlew installDebug
- ./gradlew lint
- ./gradlew ktlint
- ./gradlew test

## Libraries Used

- UI: FlexibleAdapter and Glide
- Google: AppCompat, Material, CardView, RecyclerView, Constraint, Dagger and Room
- Square: Retrofit and OkHttp
- Test: Mockito and Espresso
- Rx: RxJava and RxAndroid
- Kotlin: Kotlin, KTlint and KTX
