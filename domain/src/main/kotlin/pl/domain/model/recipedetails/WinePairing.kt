package pl.domain.model.recipedetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WinePairing(
    val wines: List<String>
) : Parcelable
