package sk.matejkosut.scratchcard

import androidx.navigation.NavHostController
import sk.matejkosut.scratchcard.ScratchCardDestinations.ACTIVATION_ROUTE
import sk.matejkosut.scratchcard.ScratchCardDestinations.HOME_ROUTE
import sk.matejkosut.scratchcard.ScratchCardDestinations.SCRATCH_ROUTE
import sk.matejkosut.scratchcard.ScratchCardScreens.ACTIVATION_SCREEN
import sk.matejkosut.scratchcard.ScratchCardScreens.HOME_SCREEN
import sk.matejkosut.scratchcard.ScratchCardScreens.SCRATCH_SCREEN

/**
 * Screens used in [ScratchCardDestinations]
 */
private object ScratchCardScreens {
    const val HOME_SCREEN = "home"
    const val SCRATCH_SCREEN = "scratch"
    const val ACTIVATION_SCREEN = "activation"
}

/**
 * Destinations used in the [ScratchCardActivity]
 */
object ScratchCardDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val SCRATCH_ROUTE = SCRATCH_SCREEN
    const val ACTIVATION_ROUTE = ACTIVATION_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class ScratchCardNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(HOME_ROUTE)
    }

    fun navigateToScratch() {
        navController.navigate(SCRATCH_ROUTE)
    }

    fun navigateToActivation() {
        navController.navigate(ACTIVATION_ROUTE)
    }
}