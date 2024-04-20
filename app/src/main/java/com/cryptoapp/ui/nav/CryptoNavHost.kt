package com.cryptoapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cryptoapp.datastore.PreferencesConstants.WELCOME_KEY
import com.cryptoapp.datastore.PreferencesManager
import com.cryptoapp.ui.screens.Screen
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsScreen
import com.cryptoapp.ui.screens.cryptolist.CryptoListScreen
import com.cryptoapp.ui.screens.welcome.CryptoWelcomeScreen

@Composable
fun CryptoNavHost(
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val preferencesData =
        remember { mutableStateOf(preferencesManager.getData(WELCOME_KEY, false)) }

    NavHost(
        navController = navController,
        startDestination = if (preferencesData.value) {
            Screen.List.path
        } else {
            Screen.Welcome.path
        }
    ) {
        composable(Screen.Welcome.path) {
            CryptoWelcomeScreen {
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
            arguments = listOf(navArgument("cryptoId") { type = NavType.IntType }),
        ) { backStackEntry ->
            val countryIndex = backStackEntry.arguments!!.getInt("cryptoId")
            CryptoDetailsScreen(
                cryptoIndex = countryIndex,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
            )
        }
    }
}