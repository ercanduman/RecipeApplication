# RecipeApplication

## End user perspective

This is an android application where the primary function of the app is to search for Recipes and display them
in a list.

In addition to searching, Recipes can also be searched and filtered by Category Chips located under the Search
Bar. There are 9 scrollable Chips to choose from, including Chicken, Beef, Soup, Pizza, and more.

#### Recipes

<img src="https://user-images.githubusercontent.com/11629459/212318486-7c976704-ee4c-423b-bfe5-e738e0a57767.png" width=30% height=30%>


When one of the recipes is clicked, a navigation will take place to the Recipe Details screen and the details
of the recipe will be displayed.

#### Recipe Details

<img src="https://user-images.githubusercontent.com/11629459/212318525-b8331b88-d83a-4977-aede-8675275c22ee.png" width=30% height=30%>

## Developer perspective

This is a demo **android** app where the primary function of the app is to search for Recipes in
the [Food2fork API](https://food2fork.ca/ "Food2fork API") and display them in a list built in Jetpack Compose
components.

When you type a search query in the search bar at the top of the screen or click a Category Chip at the bottom
of the Search Bar, RecipeApplication will call the Food2fork API, display a Shimmer loading effect and list
all recipes when fetched.

With ***3000+*** recipes, there is also a **Pagination** logic where you can have a smooth scrolling behavior
without draining phone resources.

All UI elements were created
using [Jetpack Compose](https://developer.android.com/jetpack/compose "Jetpack Compose").

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
* Retrofit library (Connecting application to back-end services (REST and JSON))
* Dagger-Hilt (for Dependency injection)
* Interacts with a public API to retrieve JSON objects
* Food2fork API (Providing JSON data based on Authorization and token keys)
* The JSON recipe objects have the following properties:

```json
{
  "pk": 583,
  "title": "Pizza Potato Skins",
  "publisher": "mitch",
  "featured_image": "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png",
  "rating": 16,
  "ingredients": [
    "Canola Oil",
    "Kosher Salt",
    "Butter, Melted",
    "Diced Pepperoni",
    "Minced Fresh Parsley",
    "Grated Mozzarella Cheese",
    "8 whole Small Russet Potatoes",
    "Jarred Marinara Or Pizza Sauce",
    "Miscellaneous Pizza Toppings: Cooked Sausage, Cooked Hamburger, Diced Bell Pepper, Diced Onion, Diced Mushrooms, Diced Canadian Bacon, Etc."
  ]
}
```

## API Usage

**Food2Fork** API details can also be checked at the following URL:
https://food2fork.ca/

#### Searching Recipes

Pagination **page size** = 30

**GET**
https://food2fork.ca/api/recipe/search/?page=2&query=beef%20carrot

**Headers**
Authorization Token 9c8b06d329136da358c2d00e76946b0111ce2c48

##### Success Response

```json
{
  "count": 118,
  "next": "http://127.0.0.1:8000/api/recipe/search/?page=3&query=beef+carrot+potato+onion",
  "previous": "https://food2fork.ca/api/recipe/search/?query=beef+carrot+potato+onion",
  "results": [
    {
      "pk": 583,
      "title": "Pizza Potato Skins",
      "publisher": "mitch",
      "featured_image": "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png",
      "rating": 16,
      "ingredients": [
        "Canola Oil",
        "Kosher Salt",
        "Butter, Melted",
        "Diced Pepperoni",
        "Minced Fresh Parsley",
        "Grated Mozzarella Cheese",
        "8 whole Small Russet Potatoes",
        "Jarred Marinara Or Pizza Sauce",
        "Miscellaneous Pizza Toppings: Cooked Sausage, Cooked Hamburger, Diced Bell Pepper, Diced Onion, Diced Mushrooms, Diced Canadian Bacon, Etc."
      ]
    },
    {
      "pk": 583,
      "title": "Pizza Potato Skins",
      "publisher": "mitch",
      "featured_image": "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png",
      "rating": 16,
      "source_url": "http://thepioneerwoman.com/cooking/2013/04/pizza-potato-skins/",
      "ingredients": [
        "Canola Oil",
        "Kosher Salt",
        "Butter, Melted",
        "Diced Pepperoni",
        "Minced Fresh Parsley",
        "Grated Mozzarella Cheese",
        "8 whole Small Russet Potatoes",
        "Jarred Marinara Or Pizza Sauce",
        "Miscellaneous Pizza Toppings: Cooked Sausage, Cooked Hamburger, Diced Bell Pepper, Diced Onion, Diced Mushrooms, Diced Canadian Bacon, Etc."
      ]
    }
  ]
}
```

**No Results**

```json
{
  "count": 0,
  "next": null,
  "previous": null,
  "results": []
}
```

### GET Recipe by ID

Find a specific recipe by referencing its unique id.

**GET**
https://food2fork.ca/api/recipe/get/?id=583

```json
{
  "pk": 583,
  "title": "Pizza Potato Skins",
  "publisher": "mitch",
  "featured_image": "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png",
  "rating": 16,
  "source_url": "http://thepioneerwoman.com/cooking/2013/04/pizza-potato-skins/",
  "ingredients": [
    "Canola Oil",
    "Kosher Salt",
    "Butter, Melted",
    "Diced Pepperoni",
    "Minced Fresh Parsley",
    "Grated Mozzarella Cheese",
    "8 whole Small Russet Potatoes",
    "Jarred Marinara Or Pizza Sauce",
    "Miscellaneous Pizza Toppings: Cooked Sausage, Cooked Hamburger, Diced Bell Pepper, Diced Onion, Diced Mushrooms, Diced Canadian Bacon, Etc."
  ]
}
```

## Download the app

<img src="https://user-images.githubusercontent.com/11629459/212318000-c955c8cd-9136-44db-9094-c7307785ff53.png" width=30% height=30%>

APK file can be found in **[/apk](https://github.com/ercanduman/RecipeApplication/tree/main/apk "/apk")**
folder as compressed** .zip** file or
under **[/debug](https://github.com/ercanduman/RecipeApplication/tree/main/apk/debug "/debug")** folder as **
apk** file.

