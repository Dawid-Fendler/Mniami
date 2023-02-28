package pl.presentation.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import pl.architecture.base.BaseFragment
import pl.login.presentation.R
import pl.login.presentation.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.singUpTitle.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            )
        }
    }
}
