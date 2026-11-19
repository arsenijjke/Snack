package app.arsenijjke.domain.usecase

import app.arsenijjke.domain.repository.CategoriesRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {

    suspend operator fun invoke() =
        categoriesRepository.getCategoriesList()
}
