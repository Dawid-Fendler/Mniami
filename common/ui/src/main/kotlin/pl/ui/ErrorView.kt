package pl.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import pl.common.ui.databinding.ViewErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = ViewErrorBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var errorText: String? = null
        set(value) {
            field = value
            binding.errorTextView.text = errorText
        }

    var imageResource: Int? = null
        set(value) {
            if (value == null) {
                binding.errorImage.isVisible = false
                return
            }
            field = value
            binding.errorImage.isVisible = true
            binding.errorImage.setImageResource(value)
        }
}
