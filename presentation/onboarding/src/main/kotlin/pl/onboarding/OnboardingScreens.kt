package pl.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class OnboardingScreens(
    @StringRes val title: Int,
    @DrawableRes val image: Int,
    @StringRes val description: Int
) {
    FIRST_SCREEN(
        title = R.string.first_onboarding_item_title,
        image = R.drawable.first_onboarding_image,
        description = R.string.first_onboarding_item_description
    ),
    SECOND_SCREEN(
        title = R.string.second_onboarding_item_title,
        image = R.drawable.second_onboarding_image,
        description = R.string.second_onboarding_item_description
    ),
    THIRD_SCREEN(
        title = R.string.third_onboarding_item_title,
        image = R.drawable.third_onboarding_image,
        description = R.string.third_onboarding_item_description
    )
}
