package com.cryptoapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cryptoapp.MainViewModel
import com.cryptoapp.preferences.PreferencesConstants.WELCOME_KEY
import com.cryptoapp.ui.screens.Screen
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsScreen
import com.cryptoapp.ui.screens.cryptolist.CryptoListScreen
import com.cryptoapp.ui.screens.splash.SplashScreen
import com.cryptoapp.ui.screens.welcome.CryptoWelcomeScreen

@Composable
fun CryptoNavHost(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    val preferencesData = viewModel.getData(WELCOME_KEY, false)

    NavHost(
        navController = navController, startDestination = Screen.Splash.path
    ) {
        composable(Screen.Welcome.path) {
            CryptoWelcomeScreen(
                viewModel = hiltViewModel()
            ) {
                navController.popBackStack()
                navController.navigate(Screen.List.path)
            }
        }
        composable(Screen.Welcome.path) {
            CryptoWelcomeScreen(
                viewModel = hiltViewModel()
            ) {
                navController.popBackStack()
                navController.navigate(Screen.List.path)
            }
        }
        composable(Screen.List.path) {
            CryptoListScreen(
                viewModel = hiltViewModel(),
                onCryptoRowTap = { cryptoId ->
                    navController.navigate("${Screen.Details.path}/$cryptoId")
                }
            )
        }
        composable(
            route = "${Screen.Details.path}/{cryptoId}",
            arguments = listOf(navArgument("cryptoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val countryIndex = backStackEntry.arguments!!.getInt("cryptoId")
            CryptoDetailsScreen(
                cryptoIndex = countryIndex,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(Screen.Splash.path) {
            SplashScreen(
                popBackStack = {
                    navController.popBackStack()
                },
                nextDestination = {
                    if (preferencesData) {
                        navController.navigate(Screen.List.path)
                    } else {
                        navController.navigate(
                            Screen.Welcome.path
                        )
                    }
                }
            )
        }
    }
}