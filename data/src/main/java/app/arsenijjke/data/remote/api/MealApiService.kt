package app.arsenijjke.data.remote.api

import app.arsenijjke.data.remote.model.MealDto
import retrofit2.http.GET

interface MealApiService {

    @GET("random.php")
    suspend fun getRandomMeal(): MealDto
}
