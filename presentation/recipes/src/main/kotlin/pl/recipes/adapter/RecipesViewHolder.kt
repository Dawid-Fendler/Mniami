package pl.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.domain.model.recipes.RecipeInfoUiModel
import pl.recipes.databinding.RowRecipesBinding

class RecipesViewHolder(
    private val binding: RowRecipesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(recipeInfo: RecipeInfoUiModel) {
        binding.recipeInfo = recipeInfo
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): RecipesViewHolder = RecipesViewHolder(
            RowRecipesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
