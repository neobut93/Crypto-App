package com.cryptoapp.ui.screens.cryptolist.cryptodetails

import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.sample.sampleCrypto
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CryptoDetailsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testCase1() {
        val mockCryptoRepository = mockk<CryptoRepository>()
        val expectedCrypto = sampleCrypto

        every { mockCryptoRepository.getCrypto(1) } returns expectedCrypto

        val sut = CryptoDetailsViewModel(mockCryptoRepository)

        assertEquals(sut.getCryptoDetails(1), expectedCrypto)
    }
}