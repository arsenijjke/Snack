package app.arsenijjke.domain.model

data class Root(
    val meals: List<Meal>,
)

data class Ingredient(val name: String, val measure: String)

data class Meal(
    val id: String,
    val name: String,
    val category: String?,
    val area: String?,
    val country: String?,
    val instructions: String?,
    val thumbnail: String?,
    val tags: List<String>,
    val youtube: String?,
    val source: String?,
    val ingredients : List<Ingredient>,
)
