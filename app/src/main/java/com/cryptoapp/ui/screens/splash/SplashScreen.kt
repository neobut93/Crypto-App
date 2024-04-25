package com.cryptoapp.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.cryptoapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    popBackStack: () -> Unit,
    nextDestination: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(1500)
        popBackStack()
        nextDestination()
    }
    val splashComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_splash))
    Box(
        modifier =
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = splashComposition,
            iterations = LottieConstants.IterateForever
        )
    }
}