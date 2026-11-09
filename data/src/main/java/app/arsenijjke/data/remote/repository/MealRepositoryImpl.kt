package app.arsenijjke.data.remote.repository

import android.util.Log
import app.arsenijjke.data.remote.api.MealApiService
import app.arsenijjke.data.remote.mapper.MealMapper
import app.arsenijjke.domain.model.Meal
import app.arsenijjke.domain.repository.MealRepository
import app.arsenijjke.domain.util.Result
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val api: MealApiService,
) : MealRepository {

    override suspend fun getRandomDish(): Result<Meal> {
        val mapper = MealMapper()
        return runCatching { api.getRandomMeal() }
            .fold(
                onSuccess = { dto ->
                    Log.d("DATA", dto.strMeal)
                    Result.Success(with(mapper) { dto.toDomain() })
                },
                onFailure = { throwable ->
                    Result.Failure(throwable)
                },
            )
    }

}
