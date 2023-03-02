package pl.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.login.presentation.R
import pl.login.presentation.databinding.FragmentLoginBinding
import pl.presentation.login.LoginViewState.LoginFailure
import pl.presentation.login.LoginViewState.LoginSuccessfully

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeIsLoginSuccessfully()
    }

    private fun setListeners() {
        binding.singUpTitle.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            )
        }
    }

    private fun observeIsLoginSuccessfully() {
        viewModel.isLoginSuccessfully.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LoginSuccessfully -> navigateToRecipesScreen()
                is LoginFailure -> showLoginFailureInformation()
            }
        }
    }

    private fun navigateToRecipesScreen() {
    // TODO next task
    }

    private fun showLoginFailureInformation() {
        Toast.makeText(
            requireContext(),
            "Registration failed, please try again!",
            Toast.LENGTH_SHORT
        ).show()
    }
}
