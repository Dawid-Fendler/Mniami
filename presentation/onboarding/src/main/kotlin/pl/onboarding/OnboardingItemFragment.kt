package pl.onboarding

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import pl.architecture.base.BaseFragment
import pl.onboarding.databinding.FragmentOnboardingItemBinding

@AndroidEntryPoint
class OnboardingItemFragment :
    BaseFragment<FragmentOnboardingItemBinding>(R.layout.fragment_onboarding_item) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numberScreen = arguments?.getInt(ONBOARDING_NUMBER_SCREEN_KEY)
        if (numberScreen != null) {
            initView(numberScreen)
        }
    }

    private fun initView(numberScreen: Int) {
        setOnboardingImage(OnboardingScreens.values()[numberScreen].image)
        setOnboardingTitle(OnboardingScreens.values()[numberScreen].title)
        setOnboardingDescription(OnboardingScreens.values()[numberScreen].description)
    }

    private fun setOnboardingImage(image: Int) {
        binding.onboardingImage.setImageResource(image)
    }

    private fun setOnboardingTitle(title: Int) {
        binding.onboardingTitle.text = requireContext().resources.getString(title)
    }

    private fun setOnboardingDescription(description: Int) {
        binding.onboardingDescription.text = requireContext().resources.getString(description)
    }

    companion object {
        private const val ONBOARDING_NUMBER_SCREEN_KEY = "ScreenNumber"
        fun newInstance(numberScreen: Int) = OnboardingItemFragment().apply {
            arguments = Bundle().apply {
                putInt(ONBOARDING_NUMBER_SCREEN_KEY, numberScreen)
            }
        }
    }
}
