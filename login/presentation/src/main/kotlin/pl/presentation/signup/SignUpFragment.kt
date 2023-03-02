package pl.presentation.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.login.presentation.R
import pl.login.presentation.databinding.FragmentSignUpBinding
import pl.presentation.signup.SignUpViewState.RegistrationFailure
import pl.presentation.signup.SignUpViewState.RegistrationSuccessfully

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeIsRegistrationSuccessfully()
    }

    private fun setListeners() {
        binding.registrationButton.setOnClickListener {
            registration()
        }
        binding.loginNowText.setOnClickListener {
            navigateToLoginScreen()
        }
    }

    private fun registration() {
        viewModel.registration(
            email = binding.usernameInput.text.toString(),
            password = binding.passwordInput.text.toString()
        )
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(
            SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
        )
    }

    private fun observeIsRegistrationSuccessfully() {
        viewModel.isRegistrationSuccessfully.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RegistrationSuccessfully -> navigateToLoginScreen()
                is RegistrationFailure -> showRegistrationFailureInformation()
            }
        }
    }

    private fun showRegistrationFailureInformation() {
        Toast.makeText(
            requireContext(),
            "Registration failed, please try again!",
            Toast.LENGTH_SHORT
        ).show()
    }
}
