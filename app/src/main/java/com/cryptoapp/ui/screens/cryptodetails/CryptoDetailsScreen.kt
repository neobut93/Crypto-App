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
import com.cryptoapp.models.Crypto
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.sample.sampleCrypto
import com.cryptoapp.sample.sampleCryptos
import com.cryptoapp.ui.screens.cryptodetails.components.CryptoDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDetailsScreen(
    cryptoIndex: Int,
    viewModel: CryptoDetailsViewModel,
    onNavigateUp: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = "getCryptoDetails") {
        viewModel.getCryptoDetails(cryptoIndex)
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
                val crypto = countryDetailsState.crypto
                CryptoDetails(
                    crypto = crypto,
                    modifier = Modifier.padding(padding),
                )
            }
            is CryptoDetailsState.Error -> {}
        }
    }
}

@Preview
@Composable
fun CryptoDetailsScreenPreview() {
    CryptoDetailsScreen(
        cryptoIndex = 0,
        viewModel = CryptoDetailsViewModel(
            repository = object : CryptoRepository {
                override val cryptos: Flow<List<Crypto>>
                    get() = MutableStateFlow(sampleCryptos).asStateFlow()

                override suspend fun fetchCryptos() {}

                override fun getCrypto(id: Int): Crypto = sampleCrypto
                                                   },
        ),
        onNavigateUp = {},
    )
}
