package pl.ingredients

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import pl.common.ui.R

object IngredientsBinder {

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(BASE_IMAGE_URL + imageUrl) {
            crossfade(durationMillis = 600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter("navigateToDetails")
    @JvmStatic
    fun navigateToDetails(view: View, id: Int) {
//        view.setOnClickListener {
//            view.findNavController()
//                .navigate()
//        }
    }

    const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
}
