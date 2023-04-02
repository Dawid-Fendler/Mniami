package pl.domain.model.recipedetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredientUiModel(
    val id: Int,
    val image: String,
    val name: String,
    val unit: String,
    val amount: Double
) : Parcelable
