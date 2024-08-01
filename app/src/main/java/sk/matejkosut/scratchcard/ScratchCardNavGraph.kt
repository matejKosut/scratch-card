package sk.matejkosut.scratchcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sk.matejkosut.scratchcard.activation.ActivationScreen
import sk.matejkosut.scratchcard.home.HomeScreen
import sk.matejkosut.scratchcard.scratch.ScratchScreen

@Composable
fun ScratchCardNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScratchCardDestinations.HOME_ROUTE,
    navActions: ScratchCardNavigationActions = remember(navController) {
        ScratchCardNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ScratchCardDestinations.HOME_ROUTE) {
            HomeScreen(
                onBack = { navController.popBackStack() },
                onScratch = { navActions.navigateToScratch() },
                onActivation = { navActions.navigateToActivation() },
            )
        }
        composable(ScratchCardDestinations.SCRATCH_ROUTE) {
            ScratchScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(ScratchCardDestinations.ACTIVATION_ROUTE) {
            ActivationScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}