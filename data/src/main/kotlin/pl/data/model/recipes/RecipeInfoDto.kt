package pl.data.model.recipes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeInfoDto(
    val id: Int,
    val image: String,
    val title: String,
    val readyInMinutes: Int,
    val healthScore: Double
)
