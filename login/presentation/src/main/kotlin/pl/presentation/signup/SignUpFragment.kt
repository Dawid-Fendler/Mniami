package pl.presentation.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.login.presentation.R
import pl.login.presentation.databinding.FragmentSignUpBinding

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.registration(
                email = binding.usernameInput.text.toString(),
                password = binding.passwordInput.text.toString()
            )
        }
        binding.loginNowText.setOnClickListener {
            findNavController().navigate(
                SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            )
        }
    }
}