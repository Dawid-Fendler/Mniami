package pl.ingredients

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.domain.model.recipedetails.ExtendedIngredientUiModel
import pl.presentation.ingredients.databinding.RowIngredientBinding

class IngredientsViewHolder(
    private val binding: RowIngredientBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(ingredient: ExtendedIngredientUiModel) {
        binding.ingredients = ingredient
    }

    companion object {
        fun from(parent: ViewGroup): IngredientsViewHolder =
            IngredientsViewHolder(
                RowIngredientBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}
