package com.cryptoapp.utils

object PriceFormatting {

    fun formatCurrentPrice(currentPrice: Double): String {
        return if (currentPrice > 1) {
            DataFormatting.formatHighPrice(currentPrice)
        } else if (currentPrice < 1 && currentPrice > 0.0001) {
            DataFormatting.formatLowPrice(currentPrice)
        } else if (currentPrice.toInt() == 1) {
            DataFormatting.formatHighPrice(currentPrice)
        }
        else {
            DataFormatting.formatLowestPrice(currentPrice)
        }
    }

    fun formatPercentageChange(currentPrice: Double): String {
        return DataFormatting.formatToPercentage(currentPrice)
    }
}