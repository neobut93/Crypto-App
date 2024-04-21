package com.cryptoapp.adapters

import com.cryptoapp.dto.CryptoDto
import com.cryptoapp.models.Crypto
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WrappedCryptoList

class CryptoAdapter {
    @WrappedCryptoList
    @FromJson
    fun fromJson(cryptoDtoList: List<CryptoDto>): List<Crypto> = cryptoDtoList.map { cryptoDto ->
        Crypto(
            id = cryptoDto.id,
            symbol = cryptoDto.symbol,
            image = cryptoDto.image,
            current_price = cryptoDto.current_price,
            price_change_24h = cryptoDto.price_change_24h,
            price_change_percentage_24h = cryptoDto.price_change_percentage_24h,
            market_cap_change_percentage_24h = cryptoDto.market_cap_change_percentage_24h,
            market_cap = cryptoDto.market_cap,
            market_cap_rank = cryptoDto.market_cap_rank,
            high_24h = cryptoDto.high_24h,
            low_24h = cryptoDto.low_24h,
        )
    }

    @ToJson
    fun toJson(@WrappedCryptoList cryptoList: List<Crypto>): List<CryptoDto> =
        cryptoList.map { crypto ->
            CryptoDto(
                id = crypto.id,
                symbol = crypto.symbol,
                image = crypto.image,
                current_price = crypto.current_price,
                price_change_24h = crypto.price_change_24h,
                price_change_percentage_24h = crypto.price_change_percentage_24h,
                market_cap_change_percentage_24h = crypto.market_cap_change_percentage_24h,
                market_cap = crypto.market_cap,
                market_cap_rank = crypto.market_cap_rank,
                high_24h = crypto.high_24h,
                low_24h = crypto.low_24h,
            )
        }
}