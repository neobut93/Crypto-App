package com.cryptoapp.utils

object CalculatorActions {

    fun calculateBuy(inputValue: Double, currentPrice: Double): Double {
        return inputValue / currentPrice
    }

    fun calculateSell(currentPrice: Double, inputValue: Double): Double {
        return currentPrice * inputValue
    }
}