package app.arsenijjke.data.remote.mapper

import app.arsenijjke.data.remote.model.MealDto
import app.arsenijjke.domain.model.Ingredient
import app.arsenijjke.domain.model.Meal

class MealMapper {

    fun MealDto.toDomain(): Meal{
        val rawIngredients = listOf(
            strIngredient1 to strMeasure1,
            strIngredient2 to strMeasure2,
            strIngredient3 to strMeasure3,
            strIngredient4 to strMeasure4,
            strIngredient5 to strMeasure5,
            strIngredient6 to strMeasure6,
            strIngredient7 to strMeasure7,
            strIngredient8 to strMeasure8,
            strIngredient9 to strMeasure9,
            strIngredient10 to strMeasure10,
            strIngredient11 to strMeasure11,
            strIngredient12 to strMeasure12,
            strIngredient13 to strMeasure13,
            strIngredient14 to strMeasure14,
            strIngredient15 to strMeasure15,
            strIngredient16 to strMeasure16,
            strIngredient17 to strMeasure17,
            strIngredient18 to strMeasure18,
            strIngredient19 to strMeasure19,
            strIngredient20 to strMeasure20,
        )
        return Meal(
            id = idMeal,
            name = strMeal,
            category = strCategory.takeIf { it.isNotBlank() },
            area = strArea?.takeIf { it.isNotBlank() },
            country = strCountry.takeIf { it.isNotBlank() },
            instructions = strInstructions,
            thumbnail = strMealThumb,
            tags = strTags?.split(',')?.filter { it.isNotBlank() } ?: emptyList(),
            youtube = strYoutube.takeIf { it.isNotBlank() },
            source = strSource.takeIf { it.isNotBlank() },
            ingredients = rawIngredients
                .filter { (name, _) -> name.isNotBlank() }
                .map { (name, measure) -> Ingredient(name.trim(), measure.trim())}
        )
    }
}
