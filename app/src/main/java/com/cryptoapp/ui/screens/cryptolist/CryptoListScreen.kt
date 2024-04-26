package com.cryptoapp.ui.screens.cryptolist

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.cryptoapp.R
import com.cryptoapp.preferences.PreferencesConstants.DATA_KEY
import com.cryptoapp.ui.screens.cryptolist.components.CryptoInfoList
import com.cryptoapp.ui.screens.error.ErrorScreen
import com.cryptoapp.ui.screens.loading.Loading

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun CryptoListScreen(
    viewModel: CryptoListViewModel,
    onCryptoRowTap: (countryId: Int) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val refreshState by viewModel.refreshState.collectAsState()
    val activity = (LocalContext.current as? Activity)
    val splashComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie))
    val pullRefreshState = rememberPullRefreshState(refreshing =
    refreshState, onRefresh = { viewModel.fetchCryptos() })
    val context = LocalContext.current


    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.size(80.dp)) {
                        LottieAnimation(
                            composition = splashComposition,
                            iterations = LottieConstants.IterateForever
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            //Log.d("GGG",viewModel.getData(DATA_KEY, true).toString() )
                            viewModel.fetchCryptos()
                            if (viewModel.getData(DATA_KEY, false)) {
                                viewModel.triggerCountrySharedFlow("AAAA")
                            }

                        },) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Help,
                            contentDescription = "help",
                        )
                    }
                }
            )
        },
    ) { padding ->
        val transition = updateTransition(
            targetState = state,
            label = "list_state_transition",
        )
        transition.Crossfade(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentKey = { it.javaClass },
        ) { state ->
            when (state) {
                is CryptoListState.Loading -> Loading()
                is CryptoListState.Success -> CryptoInfoList(
                    cryptos = state.countries,
                    onCryptoRowTap = onCryptoRowTap,
                    pullRefreshState = pullRefreshState,
                    isRefreshing = refreshState,
                    //toast = viewModel.getData(DATA_KEY, false),
                    //tapIcon = { viewModel.triggerCountrySharedFlow("AAAA") },
                    //viewModel = viewModel,
                    //onRefreshTap = viewModel::fetchCryptos
                )

                is CryptoListState.Error -> {
                    ErrorScreen(error = state.error,
                        onRetry = { viewModel.fetchCryptos() },
                        onAppClose = { activity?.finish() })
                }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.countrySharedFlow.collect { message ->
            Toast.makeText(context, "The $message country row was tapped", Toast.LENGTH_SHORT)
                .show()
        }
    }
}