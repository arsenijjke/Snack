package app.arsenijjke.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    val meals: List<MealDto>
)
