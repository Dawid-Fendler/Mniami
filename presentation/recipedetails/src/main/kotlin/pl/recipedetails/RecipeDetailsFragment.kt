package pl.recipedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.domain.model.recipedetails.RecipeDetailsUiModel
import pl.presentation.recipedetails.R
import pl.presentation.recipedetails.databinding.FragmentRecipeDetailsBinding
import pl.recipedetails.RecipeDetailsViewState.Loading
import pl.recipedetails.RecipeDetailsViewState.RecipeDetailsLoadFailure
import pl.recipedetails.RecipeDetailsViewState.RecipeDetailsLoaded
import pl.ui.parseHtml

@AndroidEntryPoint
class RecipeDetailsFragment :
    BaseFragment<FragmentRecipeDetailsBinding>(R.layout.fragment_recipe_details) {

    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        observeRecipeDetails(1)
    }

    private fun setListeners() {

    }

    private fun observeRecipeDetails(id: Int) {
        viewModel.onStart(id)
        viewModel.recipeDetailsViewState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RecipeDetailsLoaded -> showRecipeDetailsLoadedState(result.result)
                is RecipeDetailsLoadFailure -> showRecipeDetailsLoadFailureState()
                is Loading -> showLoadingState()
            }
        }
    }

    private fun showRecipeDetailsLoadedState(result: RecipeDetailsUiModel) {
        initView(result)
    }

    private fun initView(result: RecipeDetailsUiModel) {
        initRecipeImage(result.image)
        initRecipeTitle(result.title)
        initRecipeSummary(result.summary)
        initDietTypes(result.dietTypes)
        initDishTypes(result.dishTypes)
        initMinutes(result.minutes)
        initLikes(result.likes)
    }

    private fun initRecipeImage(imageUrl: String) {
        binding.recipeImageView.load(imageUrl) {
            crossfade(true)
        }
    }

    private fun initRecipeTitle(title: String) {
        binding.recipeTitleText.text = title
    }

    private fun initRecipeSummary(summary: String) {
        binding.summaryText.parseHtml(summary)
    }

    private fun initDietTypes(dietTypes: List<String>) {
        dietTypes.forEach { dietTypeName ->
            binding.dietTypeContainer.addView(
                ChipView(requireContext()).apply {
                    chipText = dietTypeName
                }
            )
        }
    }

    private fun initDishTypes(dishTypes: List<String>) {
        dishTypes.forEach { dishTypeName ->
            binding.mealTypeContainer.addView(
                ChipView(requireContext()).apply {
                    chipText = dishTypeName
                }
            )
        }
    }

    private fun initMinutes(minutes: Int) {
        binding.timeToPreparationTextView.text = minutes.toString()
    }

    private fun initLikes(likes: Int) {
        binding.likeValueTextView.text = likes.toString()
    }

    private fun showRecipeDetailsLoadFailureState() {

    }

    private fun showLoadingState() {

    }
}
