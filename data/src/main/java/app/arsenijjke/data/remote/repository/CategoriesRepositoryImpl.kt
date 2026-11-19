package app.arsenijjke.data.remote.repository

import app.arsenijjke.data.remote.api.MealApiService
import app.arsenijjke.data.util.IoDispatcher
import app.arsenijjke.domain.model.Category
import app.arsenijjke.domain.repository.CategoriesRepository
import app.arsenijjke.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val api: MealApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): CategoriesRepository {

    override suspend fun getCategoriesList(): Result<List<Category>> {
        return runCatching {
            withContext(ioDispatcher) {
                api.getCategoriesList().categories
            }
        }.fold(
            onSuccess = {
                Result.Success(it)
            },
            onFailure = { throwable ->
                Result.Failure(throwable)
            }
        )
    }

}