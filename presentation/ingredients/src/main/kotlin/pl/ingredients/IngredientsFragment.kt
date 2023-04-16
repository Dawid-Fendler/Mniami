package pl.ingredients

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.domain.model.recipedetails.ExtendedIngredientUiModel
import pl.presentation.ingredients.R
import pl.presentation.ingredients.databinding.FragmentIngredientsBinding

@AndroidEntryPoint
class IngredientsFragment :
    BaseFragment<FragmentIngredientsBinding>(R.layout.fragment_ingredients) {

    private val ingredientsAdapter by lazy { IngredientsAdapter() }
    private val arguments: IngredientsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(arguments.ingredients)
    }

    private fun initRecyclerView(ingredients: Array<ExtendedIngredientUiModel>) {
        binding.recyclerView.adapter = ingredientsAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        ingredientsAdapter.setData(ingredients.toList())
    }
}
