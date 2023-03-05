package pl.data.model.recipes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipesDto(
    @Json(name = "results") val recipes: List<RecipeInfoDto>
)
