package com.cryptoapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cryptos")
data class Crypto(
    @PrimaryKey
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
    val low_24h: Double
) : Parcelable