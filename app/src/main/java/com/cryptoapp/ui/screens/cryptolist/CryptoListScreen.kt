package com.cryptoapp.ui.screens.cryptolist

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cryptoapp.ui.components.CryptoInfoList
import com.cryptoapp.ui.components.Loading

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun CryptoListScreen(
    viewModel: CryptoListViewModel,
    onCryptoRowTap: (countryId: Int) -> Unit,
    //onAboutTap: () -> Unit,
    //onSettingsTap: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val refreshState by viewModel.refreshState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(refreshing =
    refreshState, onRefresh = { viewModel.fetchCryptos() })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TollBar") },
                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "text 1",
                        )
                    }
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Help,
                            contentDescription = "text 2",
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
                    isRefreshing = refreshState
                )
                is CryptoListState.Error -> {}
            }
        }
    }
}