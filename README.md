# RecipeApplication

## End user perspective:
The primary function of this app is to search for Recipes and display them in a list.

Recipes can also be filtered by Chips located under the Search Bar. There are 9 scrollable Chips as search categories to choose from, including Chicken, Beef, Soup, Pizza, and more.

#### Recipes

#### Recipe Details


## Developer perspective:
This is a demo **android** app where the primary function of the app is to search for Recipes in the [Food2fork API](https://food2fork.ca/ "Food2fork API") and display them in a list built in Jetpack Compose components.

When you type a search query in the search bar at the top of the screen or click a Category Chip at the bottom of the Search Bar, RecipeApplication will call the Food2fork API, display a Shimmer loading effect and list all recipes when fetched.

With ***3000+*** recipes, there is also a **Pagination** logic where you can have a smooth scrolling behavior without draining phone resources.

All UI elements were created using [Jetpack Compose](https://developer.android.com/jetpack/compose "Jetpack Compose").

This application is also structured to have **MVVM architecture** with **clean code principles**.

## Features:
* Search feature
* Category Chips
* Recipes List Fragment
* Recipes Details Fragment
* MVVM architecture
* Navigation Component
* UI Design build with Jetpack Compose
* Built the app using Kotlin language
* Displaying a placeholders for loading images
* Coroutines (Concurrency and non-blocking executions)
* Retroıfit library (Connecting application to back-end services (REST and JSON))
* Dagger-Hilt (for Dependency injection)
* Interacts with a public API to retrieve JSON objects
* Food2fork API (Providing JSON data based on Autharization and token keys)
* The JSON recipe objects have the following properties:
JSON content here:

## Download the app
APK file here
