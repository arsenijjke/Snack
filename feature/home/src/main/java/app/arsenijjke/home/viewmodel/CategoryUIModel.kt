package app.arsenijjke.home.viewmodel

import androidx.compose.runtime.Immutable
import app.arsenijjke.domain.model.Category

@Immutable
data class CategoryUIModel(
    val id: String,
    val name: String,
    val imageUri: String = "",
)

fun Category.toUiModel(): CategoryUIModel = CategoryUIModel(
    id = idCategory,
    name = strCategory,
    imageUri = strCategoryThumb,
)
