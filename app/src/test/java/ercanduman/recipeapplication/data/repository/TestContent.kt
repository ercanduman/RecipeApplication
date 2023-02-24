package ercanduman.recipeapplication.data.repository

import ercanduman.recipeapplication.data.api.model.RecipeDto

val testRecipeDto: RecipeDto = RecipeDto(
    recipeId = 70,
    rating = 31,
    title = "Peanut Butter Cake with Chocolate Icing",
    imageUrl = "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/70/featured_image.png",
    sourceUrl = "http://thepioneerwoman.com/cooking/2013/11/peanut-butter-cake-with-chocolate-icing/",
    dateAdded = "November 11 2020",
    publisher = "blake",
    description = "",
    dateUpdated = "November 11 2020",
    longDateAdded = 1606348735,
    longDateUpdated = 1606348735,
    ingredients = listOf(
        "Cake",
        "2 cups Sugar",
        "2 whole Eggs",
        "1 / 4 teaspoon Salt",
        ", 1 teaspoon Vanilla",
        "1 - 3 / 4 stick Butter",
        "1/2 cup Buttermilk",
        "Tablespoons Milk",
        "1 / 2 cup Peanut Butter, 1 teaspoon Baking Soda ",
        "2 cups All - purpose Flour, 1 / 2 cup Finely Chopped Pecans",
        "4 Tablespoons (heaping) Cocoa, 1 pound Powdered Sugar, Sifted",
        "4 Tablespoons (heaping) Cocoa Powder, 1 pound (minus 1/2 Cup) Powdered Sugar"
    )
)
