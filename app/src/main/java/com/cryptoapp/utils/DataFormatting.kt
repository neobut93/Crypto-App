package com.cryptoapp.utils

object DataFormatting {

    fun formatHighPrice(price: Double): String {
        return "%,.2f".format(price)
    }

    fun formatLowPrice(price: Double): String {
        return "%,.4f".format(price)
    }

    fun formatLowestPrice(price: Double): String {
        return "%,.5f".format(price)
    }

    fun formatToPercentage(price: Double): String {
        return "%.2f".format(price)
    }

    fun formatBuyOption(price: Double): String {
        return "%.6f".format(price)
    }

    fun formatSellOption(price: Double): String {
        return "%,.5f".format(price)
    }
}