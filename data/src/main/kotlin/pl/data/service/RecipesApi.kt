package pl.data.service

import pl.data.model.recipes.RecipesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @Query("number") numberRecipes: Int = 20,
        @Query("addRecipeInformation") recipeInformation: Boolean = true
    ): RecipesDto
}
