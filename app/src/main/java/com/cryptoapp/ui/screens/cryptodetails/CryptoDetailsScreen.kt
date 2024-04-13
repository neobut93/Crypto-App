package com.cryptoapp.ui.screens.cryptodetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cryptoapp.ui.components.CryptoDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDetailsScreen(
    countryIndex: Int,
    viewModel: CryptoDetailsViewModel,
    onNavigateUp: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = "getCountryDetails") {
        viewModel.getCountryDetails(countryIndex)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = (uiState.value as? CryptoDetailsState.Success)?.crypto?.symbol.orEmpty())
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                        )
                    }
                }
            )
        },
    ) { padding ->
        when (val countryDetailsState = uiState.value) {
            is CryptoDetailsState.Loading -> {
            }
            is CryptoDetailsState.Success -> {
                val country = countryDetailsState.crypto
                CryptoDetails(
                    crypto = country,
                    modifier = Modifier.padding(padding),
                )
            }
            is CryptoDetailsState.Error -> {}
        }

    }
}
