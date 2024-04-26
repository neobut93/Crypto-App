package com.cryptoapp.ui.screens.cryptodetails

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cryptoapp.R
import com.cryptoapp.models.Crypto
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.sample.sampleCrypto
import com.cryptoapp.sample.sampleCryptos
import com.cryptoapp.ui.screens.cryptodetails.components.CryptoDetails
import com.cryptoapp.utils.UrlRedirect.COINGECKO_URL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDetailsScreen(
    cryptoIndex: Int,
    viewModel: CryptoDetailsViewModel,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = "getCryptoDetails") {
        viewModel.getCryptoDetails(cryptoIndex)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        (uiState.value as? CryptoDetailsState.Success)?.crypto?.symbol?.let {
                            Text(
                                text = it.toUpperCase(
                                    Locale.ROOT
                                ), fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data((uiState.value as? CryptoDetailsState.Success)?.crypto?.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = stringResource(R.string.cryptoicon),
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }

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
                },
                actions = {
                    IconButton(onClick = {
                        Intent(Intent.ACTION_VIEW).also {
                            it.data =
                                Uri.parse(COINGECKO_URL + (uiState.value as? CryptoDetailsState.Success)?.crypto?.id)
                            if (it.resolveActivity(context.packageManager) != null) {
                                context.startActivity(it)
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_network),
                            contentDescription = stringResource(R.string.browse_icon)
                        )
                    }
                    IconButton(onClick = {
                        Intent(Intent.ACTION_SEND).also {
                            it.putExtra(
                                Intent.EXTRA_TEXT,
                                COINGECKO_URL + (uiState.value as? CryptoDetailsState.Success)?.crypto?.id
                            )
                            it.type = "text/plain"
                            if (it.resolveActivity(context.packageManager) != null) {
                                context.startActivity(it)
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = stringResource(R.string.share_icon)
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
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
        onNavigateUp = {})
}
