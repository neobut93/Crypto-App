package com.cryptoapp.ui.screens

sealed interface Screen {
    val path: String

    data object List : Screen {
        override val path = "list"
    }

    data object Details : Screen {
        override val path = "details"
    }

    data object Welcome : Screen {
        override val path = "welcome"
    }

    data object Splash : Screen {
        override val path = "splash"
    }
}