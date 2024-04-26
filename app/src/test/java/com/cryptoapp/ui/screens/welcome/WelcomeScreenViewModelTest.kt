package com.cryptoapp.ui.screens.welcome

import com.cryptoapp.preferences.CryptoSharedPrefs
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class WelcomeScreenViewModelTest {

    @Test
    fun `welcome view model returns correct unit`()  {
        val key = "key"
        val value = true
        val prefs = mockk<CryptoSharedPrefs>()
        val sut = WelcomeScreenViewModel(prefs)
        every { prefs.saveData(key, value) } returns Unit

        sut.saveData(key, value)
        verify { prefs.saveData(key, value) }
    }
}