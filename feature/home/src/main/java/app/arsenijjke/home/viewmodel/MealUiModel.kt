package app.arsenijjke.home.viewmodel

import app.arsenijjke.domain.model.Meal

data class MealUiModel(
    val id: String,
    val username: String,
    val mealName: String,
    val description: String,
    val typeOfMeal: String,
    val isFavourite: Boolean = false,
    val categories: List<String>,
)

fun Meal.toUiModel(
    username: String = "Username",
    isFavourite: Boolean = false,
): MealUiModel {
    return MealUiModel(
        id = id,
        username = username,
        mealName = name,
        description = instructions
            .take(80)
            .trim()
            .let { if (it.length < instructions.length) "$it..." else it },
        typeOfMeal = category,
        isFavourite = isFavourite,
        categories = tags,
    )
}
