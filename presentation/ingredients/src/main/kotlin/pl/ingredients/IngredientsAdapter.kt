package pl.ingredients

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.domain.model.recipedetails.ExtendedIngredientUiModel

class IngredientsAdapter : RecyclerView.Adapter<IngredientsViewHolder>() {

    private var ingredients = emptyList<ExtendedIngredientUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder.from(parent)
    }

    override fun getItemCount() = ingredients.size

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val current = ingredients[position]
        holder.bind(current)
    }

    fun setData(newData: List<ExtendedIngredientUiModel>) {
        val ingredientDiffUtil = IngredientsDiffUtil(ingredients, newData)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientDiffUtil)
        ingredients = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
