package pl.ingredients

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import pl.common.ui.R
import pl.domain.model.recipedetails.ExtendedIngredientUiModel

object IngredientsBinder {

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(BASE_IMAGE_URL + imageUrl) {
            crossfade(durationMillis = 600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter(value = ["ingrId", "name", "image"])
    @JvmStatic
    fun navigateToDetails(view: View, id: Int, name: String, image: String) {
        view.setOnClickListener {
            view.findNavController()
                .navigate(
                    IngredientsFragmentDirections.actionIngredientsFragmentToIngredientDetailsGraph(
                        ExtendedIngredientUiModel(id, image, name)
                    )
                )
        }
    }

    const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
}
