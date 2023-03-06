package pl.recipes.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import pl.common.ui.R

object RecipesBinding {

    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            crossfade(durationMillis = 600)
            error(R.drawable.ic_error_placeholder)
        }
    }
}
