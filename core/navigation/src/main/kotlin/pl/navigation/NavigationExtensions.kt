package pl.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.changeStartDestination(
    popUpToDestination: Int,
    action: Int,
    argument: Bundle? = null
) {
    val navOption = NavOptions.Builder()
        .setPopUpTo(popUpToDestination, true)
        .build()
    navigate(action, argument, navOption)
}

fun NavController.hideOrShowActionBarBaseOnDestination(
    hide: () -> Unit,
    show: () -> Unit,
    destinationsId: List<Int>
) {
    addOnDestinationChangedListener { _, destination, _ ->
        if (destinationsId.contains(destination.id)) {
            hide()
        } else {
            show()
        }
    }
}
