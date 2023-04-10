package pl.recipedetails

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.domain.model.recipedetails.ExtendedIngredientUiModel
import pl.domain.model.recipedetails.RecipeDetailsUiModel
import pl.domain.model.recipedetails.WinePairingUiModel
import pl.presentation.recipedetails.R
import pl.presentation.recipedetails.databinding.FragmentRecipeDetailsBinding
import pl.recipedetails.RecipeDetailsViewState.Loading
import pl.recipedetails.RecipeDetailsViewState.RecipeDetailsLoadFailure
import pl.recipedetails.RecipeDetailsViewState.RecipeDetailsLoaded
import pl.ui.parseHtml

@AndroidEntryPoint
@SuppressWarnings("TooManyFunctions")
class RecipeDetailsFragment :
    BaseFragment<FragmentRecipeDetailsBinding>(R.layout.fragment_recipe_details) {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private val arguments: RecipeDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeRecipeDetails(arguments.id)
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
        initScreenOptions(animationViewVisible = false, rootViewVisible = true)
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
        initHealthScore(result.healthScore)
        initWinesButton(result.winePairing)
        initIngredientsButton(result.ingredients)
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
        val ll: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        ll.setMargins(8, 0, 0, 0)
        dietTypes.forEach { dietTypeName ->
            binding.dietTypeContainer.addView(
                ChipView(requireContext()).apply {
                    chipText = dietTypeName
                    layoutParams = ll
                }
            )
        }
    }

    private fun initDishTypes(dishTypes: List<String>) {
        val ll: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        ll.setMargins(8, 0, 0, 0)
        dishTypes.forEach { dishTypeName ->
            binding.mealTypeContainer.addView(
                ChipView(requireContext()).apply {
                    chipText = dishTypeName
                    layoutParams = ll
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

    private fun initHealthScore(score: Float) {
        binding.recipeRatingTextView.text = score.toString()
    }

    private fun initWinesButton(wines: WinePairingUiModel) {
        binding.openWinesScreenButton.setOnClickListener {
            findNavController().navigate(
                RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToWinesDialogFragment(
                    wines
                )
            )
        }
    }

    private fun initIngredientsButton(ingredients: List<ExtendedIngredientUiModel>) {
        binding.openIngredientsScreenButton.setOnClickListener {
            findNavController().navigate(
                RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToIngredientsGraph(
                    ingredients.toTypedArray()
                )
            )
        }
    }

    private fun showRecipeDetailsLoadFailureState() {
        initScreenOptions(animationViewVisible = false, rootViewVisible = false)
    }

    private fun showLoadingState() {
        initScreenOptions()
    }

    private fun initScreenOptions(
        animationViewVisible: Boolean = true,
        rootViewVisible: Boolean = false
    ) {
        with(binding) {
            animationView.isVisible = animationViewVisible
            scrollView.isVisible = rootViewVisible
        }
    }
}
