package com.cryptoapp.sample

import com.cryptoapp.models.Crypto

val sampleCryptos = listOf(
    Crypto(
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
        //total_volume = 50963000894,
    ),
    Crypto(
        id = "ethereum",
        symbol = "etc",
        image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
        current_price = 3152.87,
        price_change_24h = 95.3,
        price_change_percentage_24h = 3.11694,
        market_cap_change_percentage_24h = 4.45803,
        market_cap = 379835920112,
        market_cap_rank = 2,
        high_24h = 3165.94,
        low_24h = 2914.61,
        //total_volume = 26897980179,
    ),
    Crypto(
        id = "solana",
        symbol = "sol",
        image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
        current_price = 150.46,
        price_change_24h = 8.93,
        price_change_percentage_24h = 6.31082,
        market_cap_change_percentage_24h = 6.0496,
        market_cap = 67427398209,
        market_cap_rank = 3,
        high_24h = 150.54,
        low_24h = 131.57,
        //total_volume = 8623242824,
    )
)

val sampleCrypto = Crypto(
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
    //total_volume = 50963000894,
)