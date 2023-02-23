package pl.login

import com.example.login.R
import com.example.login.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>(R.layout.login_fragment)
