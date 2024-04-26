package com.cryptoapp.ui.screens.cryptolist.components

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.models.Crypto
import com.cryptoapp.sample.sampleCryptos

@Composable
fun CryptoInfoList(
    cryptos: List<Crypto>,
    onCryptoRowTap: (countryId: Int) -> Unit,
    pullRefreshState: PullRefreshState,
    isRefreshing: Boolean
) {
    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "#", modifier = Modifier.weight(0.2f))
                Text(text = "Coin", modifier = Modifier.weight(0.5f))
                Text(text = "Price", modifier = Modifier.weight(0.4f))
                Text(text = "24h", modifier = Modifier.weight(0.3f))
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(cryptos) { id, crypto ->
                    CryptoInfoRow(
                        crypto = crypto,
                        onTap = {
                            onCryptoRowTap(id)
                        },
                    )
                    Divider(color = Color(0xFFCECED5))
                }
            }

        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = isRefreshing, state = pullRefreshState
        )
    }
}

@Preview
@Composable
fun CryptoInfoListPreview() {
    CryptoInfoList(
        cryptos = sampleCryptos,
        onCryptoRowTap = {},
        pullRefreshState = rememberPullRefreshState(refreshing = true, onRefresh = { }),
        isRefreshing = false
    )
}
