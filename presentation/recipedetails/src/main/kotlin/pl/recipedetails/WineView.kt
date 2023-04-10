package pl.recipedetails

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import pl.presentation.recipedetails.databinding.ViewWineBinding

class WineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = ViewWineBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var chipText: String? = null
        set(value) {
            field = value
            binding.chipItem.text = chipText
        }
}
