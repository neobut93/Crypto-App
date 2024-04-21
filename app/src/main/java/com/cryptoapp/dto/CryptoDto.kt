package com.cryptoapp.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoDto(
    val id: String,
    val symbol: String,
    val image: String,
    val current_price: Double,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap: Long,
    val market_cap_rank: Int,
    val high_24h: Double,
    val low_24h: Double,
)