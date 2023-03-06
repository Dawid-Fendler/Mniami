package pl.recipes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.domain.model.recipes.RecipeInfoUiModel

class RecipesAdapter : RecyclerView.Adapter<RecipesViewHolder>() {

    private var recipes = emptyList<RecipeInfoUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder.from(parent)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipes = recipes[position]
        holder.bind(currentRecipes)
    }

    fun setData(newData: List<RecipeInfoUiModel>) {
        val recipesDiffUtil = RecipesDiffUtil(recipes, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
