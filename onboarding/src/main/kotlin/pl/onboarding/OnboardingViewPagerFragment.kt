package pl.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.onboarding.R
import com.example.onboarding.databinding.FragmentOnboardingViewPagerBinding
import pl.architecture.base.BaseFragment
import pl.onboarding.adapter.OnboardingViewPagerAdapter

class OnboardingViewPagerFragment :
    BaseFragment<FragmentOnboardingViewPagerBinding>(R.layout.fragment_onboarding_view_pager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initFinishButton()
        setListeners()
    }

    private fun initViewPager() {
        val fragments = listOf(
            OnboardingItemFragment.newInstance(0),
            OnboardingItemFragment.newInstance(1),
            OnboardingItemFragment.newInstance(2)
        )
        binding.viewPager.adapter = OnboardingViewPagerAdapter(
            fragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.springDotsIndicator.attachTo(binding.viewPager)
    }

    private fun setListeners() {
        binding.finishButton.setOnClickListener {}
    }

    private fun initFinishButton() {
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.finishButton.isVisible = position == 2
            }
        })
    }
}
