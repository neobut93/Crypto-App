package com.cryptoapp.ui.screens.cryptodetails.calculator

sealed class CalculatorOperation(val operator: String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("x")
    object Divide : CalculatorOperation("/")
}
