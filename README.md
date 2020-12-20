# UnsplashProject
An app that allows to view, search, download high-quality photos using Unsplash API

Separation of concerns, high level of abstraction and the dependency rule, which in our case means that layers only know about what's directly underneath them:

* Presentation layer knows about use cases (domain layer).
* Domain layer knows about repository (data layer) but not the Presentation layer.
* Data layer doesn't know about domain or presentation layers.

This allows for easier testing and maintenance and recommended for bigger projects (alongside modularization).
## Applied concepts
* Arch. patterns: MVVM, Clean Architecture 
* Kotlin Coroutines for background operations
* Koin as dependency injection
* Paging Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources.
* Network: Retrofit in order to simplify requesting data from REST API
* A presentation layer that contains a fragment (View) and a ViewModel per screen (or feature).
* A data layer with a repository and two data sources (local using Room and remote using Retrofit)


## Screenshots

<img src="screen/main.jpg" width="230"/> <img src="screen/search.jpg" width="230"/>  <img src="screen/detail1.jpg" width="230"/> <img src="screen/detail2.jpg" width="230"/> 



