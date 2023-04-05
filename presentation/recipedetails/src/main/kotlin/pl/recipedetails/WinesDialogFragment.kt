package pl.recipedetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import pl.architecture.base.BaseDialogFragment
import pl.presentation.recipedetails.R
import pl.presentation.recipedetails.databinding.DialogFragmentWinesBinding

class WinesDialogFragment :
    BaseDialogFragment<DialogFragmentWinesBinding>(R.layout.dialog_fragment_wines) {

    private val arguments: WinesDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initWineView()
        initCancelIconOnClick()
    }

    private fun initWineView() {
        arguments.wines.wines.forEach { wineName ->
            binding.winesContainer.addView(WineView(requireContext()).apply { chipText = wineName })
        }
    }

    private fun initCancelIconOnClick() {
        binding.cancelIcon.setOnClickListener { dismiss() }
    }
}