package pl.ingredientdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.domain.model.ingredientdetails.EstimatedCostUiModel
import pl.domain.model.ingredientdetails.IngredientFullDetailsUiModel
import pl.ingredientdetails.IngredientDetailsViewState.IngredientDetailsLoadFailure
import pl.ingredientdetails.IngredientDetailsViewState.IngredientDetailsLoaded
import pl.ingredientdetails.IngredientDetailsViewState.Loading
import pl.presentation.ingredientsdetails.R
import pl.presentation.ingredientsdetails.databinding.FragmentIngredientDetailsBinding

@AndroidEntryPoint
class IngredientDetailsFragment :
    BaseFragment<FragmentIngredientDetailsBinding>(R.layout.fragment_ingredient_details) {

    private val viewModel: IngredientDetailsViewModel by viewModels()
    private val arguments: IngredientDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeIngredientDetails()
        viewModel.onStart(arguments.ingredientDetails.id, arguments.ingredientDetails.name)
    }

    private fun observeIngredientDetails() {
        viewModel.ingredientDetailsViewState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is IngredientDetailsLoaded -> showIngredientDetailsLoadedState(result.result)
                is IngredientDetailsLoadFailure -> showIngredientDetailsLoadFailureState()
                is Loading -> showLoadingState()
            }
        }
    }

    private fun showIngredientDetailsLoadedState(result: IngredientFullDetailsUiModel) {
        initScreenOptions(animationViewVisible = false, rootViewVisible = true)
        initView(result)
    }

    private fun initView(result: IngredientFullDetailsUiModel) {
        initIngredientImage(result.image)
        initIngredientName(result.name)
        initIngredientAmount(result.amount)
        initIngredientConsistency(result.consistency)
        initIngredientUnits(result.possibleUnits)
        initIngredientCategory(result.categoryPath)
        initIngredientEstimatedCost(result.estimateCost)
        initIngredientSubstitutes(result.substitutes)
    }

    private fun initIngredientImage(url: String) {
        binding.ingredientImage.load(BASE_IMAGE_URL + url) {
            crossfade(600)
            error(pl.common.ui.R.drawable.ic_error_placeholder)
        }
    }

    private fun initIngredientName(name: String) {
        binding.ingredientName.text = name
    }

    private fun initIngredientAmount(amount: Double) {
        binding.amountText.text =
            requireContext().resources.getString(R.string.amount_title, amount.toString())
    }

    private fun initIngredientConsistency(consistency: String) {
        binding.consistencyText.text =
            requireContext().resources.getString(R.string.consistency_title, consistency)
    }

    private fun initIngredientUnits(units: List<String>) {
        binding.unitsText.text =
            requireContext().resources.getString(
                R.string.ingredients_unit,
                units.createFormattedText()
            )
    }

    private fun initIngredientCategory(categories: List<String>?) {
        if (categories.isNullOrEmpty()) {
            binding.categoryText.isVisible = false
            return
        }
        binding.categoryText.text =
            requireContext().resources.getString(
                R.string.categories_title,
                categories.createFormattedText()
            )
    }

    private fun initIngredientEstimatedCost(estimatedCost: EstimatedCostUiModel) {
        binding.estimatedText.text =
            requireContext().resources.getString(
                R.string.estimate_title,
                "Value:${estimatedCost.value} ${estimatedCost.unit}"
            )
    }

    private fun initIngredientSubstitutes(substitutes: List<String>) {
        binding.substitutesText.text =
            requireContext().resources.getString(
                R.string.substitutes_title,
                substitutes.createFormattedText()
            )
    }

    private fun showIngredientDetailsLoadFailureState() {
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

    companion object {
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
    }

    private fun List<String>.createFormattedText(): String {
        var text = ""
        forEach { element ->
            if (element == last()) {
                text = "- $element"
                return@forEach
            }
            text = "-$element\n"
        }
        return text
    }
}
