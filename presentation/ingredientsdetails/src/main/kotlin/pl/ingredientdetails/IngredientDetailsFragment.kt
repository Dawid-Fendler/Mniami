package pl.ingredientdetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.presentation.ingredientsdetails.R
import pl.presentation.ingredientsdetails.databinding.FragmentIngredientDetailsBinding

@AndroidEntryPoint
class IngredientDetailsFragment :
    BaseFragment<FragmentIngredientDetailsBinding>(R.layout.fragment_ingredient_details) {

    private val viewModel: IngredientDetailsViewModel by viewModels()
    private val arguments: IngredientDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.ingredientDetailsViewState.observe(viewLifecycleOwner) {
            when (it) {
                is IngredientDetailsViewState.IngredientDetailsLoaded -> {
                    Log.d("Testowo", "${it.result}")
                }

                is IngredientDetailsViewState.IngredientDetailsLoadFailure -> {
                    Log.d("Testowo", "${it.message}")
                }
            }
        }
        viewModel.onStart(arguments.ingredientDetails.id, arguments.ingredientDetails.name)
    }
}
