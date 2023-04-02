package pl.recipes.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import pl.common.ui.R
import pl.recipes.RecipesFragmentDirections

object RecipesBinding {

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(durationMillis = 600)
            error(R.drawable.ic_error_placeholder)
        }
    }

    @BindingAdapter("navigateToDetails")
    @JvmStatic
    fun navigateToDetails(view: View, id: Int) {
        view.setOnClickListener {
            view.findNavController()
                .navigate(
                    RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsGraph(id)
                )
        }
    }
}
