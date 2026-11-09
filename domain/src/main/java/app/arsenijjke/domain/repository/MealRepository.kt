package app.arsenijjke.domain.repository

import app.arsenijjke.domain.model.Meal
import app.arsenijjke.domain.util.Result

interface MealRepository {
    suspend fun getRandomDish(): Result<Meal>
}