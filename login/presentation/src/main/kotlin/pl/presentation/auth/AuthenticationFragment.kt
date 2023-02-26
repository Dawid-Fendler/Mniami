package pl.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import pl.architecture.base.BaseFragment
import pl.login.presentation.R
import pl.login.presentation.databinding.FragmentAuthenticationBinding

class AuthenticationFragment :
    BaseFragment<FragmentAuthenticationBinding>(R.layout.fragment_authentication) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(
                AuthenticationFragmentDirections.actionAuthenticationFragmentToSignUpFragment()
            )
        }
    }
}
