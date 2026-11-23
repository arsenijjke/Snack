package app.arsenijjke.domain.usecase

import app.arsenijjke.domain.model.Meal
import app.arsenijjke.domain.repository.MealRepository
import app.arsenijjke.domain.util.Result
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(): Result<Meal> =
        repository.getRandomMeal()
}
