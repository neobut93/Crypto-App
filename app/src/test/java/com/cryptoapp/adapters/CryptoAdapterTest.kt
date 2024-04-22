package com.cryptoapp.adapters

import com.cryptoapp.dto.CryptoDto
import com.cryptoapp.models.Crypto
import org.junit.Assert.*
import org.junit.Test

class CryptoAdapterTest {

    val cryptoDto = CryptoDto(
        id = "bitcoin",
        symbol = "btc",
        image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
        current_price = 65204.00,
        price_change_24h = -149.79282698971656,
        price_change_percentage_24h = -0.2292,
        market_cap_change_percentage_24h = 1.22956,
        market_cap = 1286856118639,
        market_cap_rank = 1,
        high_24h = 65514.00,
        low_24h = 62138.00,
    )

    val crypto = Crypto(
        id = "bitcoin",
        symbol = "btc",
        image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
        current_price = 65204.00,
        price_change_24h = -149.79282698971656,
        price_change_percentage_24h = -0.2292,
        market_cap_change_percentage_24h = 1.22956,
        market_cap = 1286856118639,
        market_cap_rank = 1,
        high_24h = 65514.00,
        low_24h = 62138.00,
    )

    @Test
    fun fromJsonTest() {
        val dtoList = listOf(cryptoDto)
        val cryptoList = listOf(crypto)

        val adapter = CryptoAdapter()
        val result = adapter.fromJson(dtoList)
        assertEquals(result, cryptoList)
    }

    @Test
    fun toJsonTest() {
        val dtoList = listOf(cryptoDto)
        val cryptoList = listOf(crypto)

        val adapter = CryptoAdapter()
        val result = adapter.toJson(cryptoList)
        assertEquals(result, dtoList)
    }
}

