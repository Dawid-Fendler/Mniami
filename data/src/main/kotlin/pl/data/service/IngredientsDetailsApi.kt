package pl.data.service

import pl.data.model.ingredientdetails.IngredientDetailsDto
import pl.data.model.ingredientdetails.IngredientSubstitutesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IngredientsDetailsApi {

    @GET("/food/ingredients/{id}/information")
    suspend fun getIngredientDetails(
        @Path("id") id: Int
    ): IngredientDetailsDto

    @GET("/food/ingredients/substitutes")
    suspend fun getIngredientSubstitutes(
        @Query("ingredientName") name: String
    ): IngredientSubstitutesDto
}
