package pl.recipedetails

import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.presentation.recipedetails.R
import pl.presentation.recipedetails.databinding.FragmentRecipeDetailsBinding

@AndroidEntryPoint
class RecipeDetailsFragment :
    BaseFragment<FragmentRecipeDetailsBinding>(R.layout.fragment_recipe_details)