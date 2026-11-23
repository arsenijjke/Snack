package app.arsenijjke.data.remote.repository

import app.arsenijjke.data.remote.api.MealApiService
import app.arsenijjke.data.remote.mapper.MealMapper
import app.arsenijjke.data.util.IoDispatcher
import app.arsenijjke.domain.model.Meal
import app.arsenijjke.domain.repository.MealRepository
import app.arsenijjke.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val api: MealApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MealRepository {

    override suspend fun getRandomMeal(): Result<Meal> {
        val mapper = MealMapper()
        return runCatching {
            withContext(ioDispatcher) {
                api.getRandomMeal().meals.first()
            }
        }
            .fold(
                onSuccess = { dto ->
                    Result.Success(with(mapper) { dto.toDomain() })
                },
                onFailure = { throwable ->
                    Result.Failure(throwable)
                },
            )
    }

}
