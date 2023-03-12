package pl.login.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.login.R
import pl.login.databinding.FragmentSignUpBinding
import pl.login.signup.SignUpViewState.RegistrationFailure
import pl.login.signup.SignUpViewState.RegistrationSuccessfully
import pl.login.signup.SignUpViewState.RegistrationValidationError

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
            password = binding.passwordInput.text.toString(),
            repeatedPassword = binding.confirmPasswordInput.toString()
        )
    }

    private fun observeIsRegistrationSuccessfully() {
        viewModel.isRegistrationSuccessfully.observe(viewLifecycleOwner) { result ->
            binding.validationText.isVisible = false
            when (result) {
                is RegistrationSuccessfully -> navigateToLoginScreen()
                is RegistrationFailure -> showRegistrationFailureInformation()
                is RegistrationValidationError -> showRegistrationValidationError(result.message)
            }
        }
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(
            SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
        )
    }

    private fun showRegistrationFailureInformation() {
        Toast.makeText(
            requireContext(),
            "Registration failed, please try again!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showRegistrationValidationError(message: String) {
        binding.validationText.text = message
        binding.validationText.isVisible = message.isNotEmpty()
    }
}
