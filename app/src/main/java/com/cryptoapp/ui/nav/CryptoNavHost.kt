package com.cryptoapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cryptoapp.ui.screens.Screen
import com.cryptoapp.ui.screens.cryptolist.CryptoListScreen

@Composable
fun CryptoNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.List.path) {
        composable(Screen.List.path) {
            CryptoListScreen(
                viewModel = hiltViewModel())
//                onCountryRowTap = { countryIndex ->
//                    navController.navigate("${Screen.Details.path}/$countryIndex")
//                },
//                onAboutTap = { navController.navigate(Screen.About.path) },
//                onSettingsTap = { navController.navigate(Screen.Settings.path) },
//            )
        }
//
//        composable(
//            route = "${Screen.Details.path}/{countryIndex}",
//            arguments = listOf(navArgument("countryIndex") { type = NavType.IntType }),
//        ) { backStackEntry ->
//            val countryIndex = backStackEntry.arguments!!.getInt("countryIndex")
//            CountryDetailsScreen(
//                countryIndex = countryIndex,
//                viewModel = hiltViewModel(),
//                onNavigateUp = { navController.navigateUp() },
//            )
//        }
//
//        composable(Screen.About.path) {
//            AboutScreen(
//                onNavigateUp = { navController.navigateUp() },
//            )
//        }
//
//        composable(Screen.Settings.path) {
//            SettingsScreen(
//                onNavigateUp = { navController.navigateUp() },
//                viewModel = hiltViewModel()
//            )
//        }
    }
}