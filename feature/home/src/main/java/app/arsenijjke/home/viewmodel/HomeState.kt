package app.arsenijjke.home.viewmodel

data class HomeState(
    val timeOfTheDay: String = "Evening",
    val name: String = "Ars",
    val searchQuery: String? = null,
    val categories: String,
    val recipe: Recipe,
)

data class Recipe(
    val name: String,
    val timeToCook: String,
    val type: String = "Dinner",
    val description: String,
)
