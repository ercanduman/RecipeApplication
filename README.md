# RecipeApp

This is a demo android app that gets data from **Food2Fork** API and displays in **Compose** components.

Food2Fork API details: https://food2fork.ca/

### Search Recipes

Keyword search for recipes.
**GET**
https://food2fork.ca/api/recipe/search/?page=2&query=beef carrot potato onion

##### Headers

Authorization Token 9c8b06d329136da358c2d00e76946b0111ce2c48

##### Pagination

Page size = 30
##### Success Response
`{
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
"source_url": "http://thepioneerwoman.com/cooking/2013/04/pizza-potato-skins/",
"description": "N/A",
"cooking_instructions": null,
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
],
"date_added": "November 11 2020",
"date_updated": "November 11 2020",
"long_date_added": 1606349126,
"long_date_updated": 1606349126
},
{
"pk": 584,
"title": "Loaded Sweet Potato Nachos",
"publisher": "mitch",
"featured_image": "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/584/featured_image.png",
"rating": 9,
"source_url": "http://www.twopeasandtheirpod.com/loaded-sweet-potato-nachos/",
"description": "N/A",
"cooking_instructions": null,
"ingredients": [
"1 tablespoon olive oil",
"1/4 cup chopped cilantro",
"Salt and pepper, to taste",
"Sour cream or Greek yogurt",
"1/4 cup chopped green onions",
"1 1/2 cups shredded Cheddar cheese",
"1 cup black beans, drained and rinsed",
"1 large avocado, pit removed and diced",
"3 large sweet potatoes, washed and sliced into 1/4-inch rounds"
],
"date_added": "November 11 2020",
"date_updated": "November 11 2020",
"long_date_added": 1606349126,
"long_date_updated": 1606349126
},
]
}`

###### No Results
`{
"count": 0,
"next": null,
"previous": null,
"results": []
}`

### GET Recipe by ID
Find a specific recipe by referencing its unique id.
**GET**
URL= https://food2fork.ca/api/recipe/get/?id=9

`{
"pk": 583,
"title": "Pizza Potato Skins",
"publisher": "mitch",
"featured_image": "https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png",
"rating": 16,
"source_url": "http://thepioneerwoman.com/cooking/2013/04/pizza-potato-skins/",
"description": "N/A",
"cooking_instructions": null,
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
],
"date_added": "November 11 2020",
"date_updated": "November 11 2020",
"long_date_added": 1606349126,
"long_date_updated": 1606349126
}`