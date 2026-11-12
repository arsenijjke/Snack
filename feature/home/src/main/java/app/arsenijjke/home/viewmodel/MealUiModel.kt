package app.arsenijjke.home.viewmodel

import app.arsenijjke.domain.model.Meal
import app.arsenijjke.home.viewmodel.TimeConstants.MAX_TIME_STEPS
import app.arsenijjke.home.viewmodel.TimeConstants.MIN_TIME_STEPS
import kotlin.random.Random

data class MealUiModel(
    val id: String,
    val username: String,
    val mealName: String,
    val description: String,
    val typeOfMeal: String,
    val isFavourite: Boolean = false,
    val categories: List<String>,
    val timeToCook: String,
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
        timeToCook = randomCookTime()
    )
}


/**
 * Temporary solution because api isn't providing approximate time to cook meal
 * Located here to not create UseCase with Random.Int
 */
private fun randomCookTime(): String {
    val steps = Random.nextInt(MIN_TIME_STEPS, MAX_TIME_STEPS)
    val minutes = steps * 10

    return when {
        minutes < 60 -> "${minutes} min"
        minutes == 60 -> "1 hr"
        minutes % 60 == 0 -> "${minutes / 60} hrs"
        else -> "${minutes / 60} hr ${minutes % 60} min"
    }

}

object TimeConstants {
    const val MIN_TIME_STEPS = 30
    const val MAX_TIME_STEPS = 30
}
