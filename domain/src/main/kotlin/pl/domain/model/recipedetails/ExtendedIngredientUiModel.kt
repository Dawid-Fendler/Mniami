package pl.domain.model.recipedetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredientUiModel(
    val id: Int,
    val image: String
) : Parcelable
