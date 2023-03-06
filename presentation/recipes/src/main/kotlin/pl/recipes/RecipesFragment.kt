package pl.recipes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.domain.model.recipes.RecipeInfoUiModel
import pl.recipes.RecipesViewState.Loading
import pl.recipes.RecipesViewState.RecipesEmpty
import pl.recipes.RecipesViewState.RecipesLoadFailure
import pl.recipes.RecipesViewState.RecipesLoaded
import pl.recipes.adapter.RecipesAdapter
import pl.recipes.databinding.FragmentRecipesBinding

@AndroidEntryPoint
class RecipesFragment : BaseFragment<FragmentRecipesBinding>(R.layout.fragment_recipes) {

    private val viewModel: RecipesViewModel by viewModels()
    private val recipesAdapter by lazy { RecipesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setListeners()

        observeProgressLoadingEvent()
        observeRecipesViewState()
        viewModel.start()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.start()
        }
    }

    private fun observeProgressLoadingEvent() {
        viewModel.progressLoadingEvent.observe(viewLifecycleOwner) { isRefreshing ->
            binding.swipeRefreshLayout.isRefreshing = isRefreshing
        }
    }

    private fun observeRecipesViewState() {
        viewModel.recipesViewState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RecipesLoaded -> showRecipesLoadedState(result.result)
                is Loading -> showLoadingState()
                is RecipesEmpty -> showRecipesEmptyState()
                is RecipesLoadFailure -> showRecipesLoadFailureState()
            }
        }
    }

    private fun showRecipesLoadedState(recipes: List<RecipeInfoUiModel>) {
        initScreenOptions(refreshLayoutIsVisible = true)
        recipesAdapter.setData(recipes)
    }

    private fun showLoadingState() {
        initScreenOptions()
    }

    private fun showRecipesEmptyState() {
        initScreenOptions(
            errorViewIsVisible = true,
            errorViewText = requireContext().resources.getString(R.string.recipes_empty_text)
        )
    }

    private fun showRecipesLoadFailureState() {
        initScreenOptions(rootLayoutIsVisible = false)
        Toast.makeText(
            requireContext(),
            requireContext().resources.getText(R.string.recipes_refresh_screen_text),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun initScreenOptions(
        refreshLayoutIsVisible: Boolean = false,
        rootLayoutIsVisible: Boolean = true,
        errorViewIsVisible: Boolean = false,
        errorViewText: String = ""
    ) {
        with(binding) {
            swipeRefreshLayout.isVisible = refreshLayoutIsVisible
            rootLayout.isVisible = rootLayoutIsVisible
            errorView.isVisible = errorViewIsVisible
            errorView.errorText = errorViewText
        }
    }
}
