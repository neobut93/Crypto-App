package com.cryptoapp.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cryptoapp.ui.screens.Screen
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsScreen
import com.cryptoapp.ui.screens.cryptolist.CryptoListScreen
import com.cryptoapp.ui.screens.welcome.CryptoWelcomeScreen
import com.cryptoapp.ui.screens.welcome.WelcomeViewModel
import com.cryptoapp.datastore.CryptoPrefsImpl

@Composable
fun CryptoNavHost(
    viewModel: WelcomeViewModel
) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val prefs = CryptoPrefsImpl(context)

    // set start screen as splash and then add logic to that screen to decide the startDestination

    val welcomeBoolean by viewModel.getWelcome().collectAsState(initial = true)

    Log.d("GGG", welcomeBoolean.toString())


    NavHost(navController = navController, startDestination = if(welcomeBoolean) Screen.Welcome.path else Screen.List.path) {

        composable(Screen.Welcome.path) {
            CryptoWelcomeScreen(viewModel = hiltViewModel(),
                navigate = {navController.navigate(Screen.List.path)}
                )
        }
        composable(Screen.List.path) {
                CryptoListScreen(
                    viewModel = hiltViewModel(),
                    onCryptoRowTap = { cryptoId ->
                        navController.navigate("${Screen.Details.path}/$cryptoId")
                    }
                    //onAboutTap = { navController.navigate(Screen.About.path) },
                    //onSettingsTap = { navController.navigate(Screen.Settings.path) },
                )

        }

        composable(
            route = "${Screen.Details.path}/{cryptoId}",
            arguments = listOf(navArgument("cryptoId") { type = NavType.IntType }),
        ) { backStackEntry ->
            val countryIndex = backStackEntry.arguments!!.getInt("cryptoId")
            CryptoDetailsScreen(
                countryIndex = countryIndex,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
            )
        }
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