package pl.mniami

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import dagger.hilt.android.AndroidEntryPoint
import pl.mniami.databinding.ActivityMainBinding
import pl.navigation.changeStartDestination
import pl.navigation.hideOrShowActionBarBaseOnDestination
import pl.onboarding.OnboardingViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initNavController()
        viewModel.getOnboardingDisplayed()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initNavController() {
        viewModel.onboardingDisplayed.observe(this) { isDisplayedOnboarding ->
            navController = findNavController(R.id.nav_host_fragment).apply {
                if (isDisplayedOnboarding) {
                    changeStartDestination(
                        popUpToDestination = R.id.onboardingViewPagerFragment,
                        action = R.id.action_onboardingViewPagerFragment_to_auth_graph
                    )
                }
                hideOrShowActionBarBaseOnDestination(
                    hide = { supportActionBar?.hide() },
                    show = { supportActionBar?.show() },
                    destinationsId = listOf(
                        R.id.onboardingViewPagerFragment,
                        pl.login.presentation.R.id.authenticationFragment,
                        pl.login.presentation.R.id.signUpFragment,
                        pl.login.presentation.R.id.loginFragment
                    )
                )
            }
        }
    }
}
