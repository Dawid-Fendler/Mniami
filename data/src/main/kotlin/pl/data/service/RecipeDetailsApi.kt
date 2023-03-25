package pl.data.service

import pl.data.model.recipedetails.RecipeDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeDetailsApi {

    @GET("/recipes/{id}/information")
    suspend fun getRecipeDetails(
        @Path("id") id: Int
    ): RecipeDetailsDto
}
