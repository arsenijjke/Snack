package app.arsenijjke.domain.repository

import app.arsenijjke.domain.model.Category
import app.arsenijjke.domain.util.Result

interface CategoriesRepository {

    suspend fun getCategoriesList(): Result<List<Category>>
}
