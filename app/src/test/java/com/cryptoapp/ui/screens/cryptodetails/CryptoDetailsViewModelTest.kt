package com.cryptoapp.ui.screens.cryptodetails

import app.cash.turbine.test
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.sample.sampleCrypto
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsState
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CryptoDetailsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockCryptoRepository = mockk<CryptoRepository>(relaxed = true)

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
    fun `loading state return loading`() = runBlocking {
        val sut = CryptoDetailsViewModel(mockCryptoRepository)

        sut.uiState.test {
            assertEquals(CryptoDetailsState.Loading, awaitItem())
        }
    }

    @Test
    fun `success state returns success`() = runBlocking {
        val cryptoId = 1
        val crypto = sampleCrypto
        val sut = CryptoDetailsViewModel(mockCryptoRepository)

        every { mockCryptoRepository.getCrypto(cryptoId) } returns crypto

        sut.getCryptoDetails(cryptoId)

        sut.uiState.test {
            assertEquals(CryptoDetailsState.Success(crypto), awaitItem())
        }
    }

    @Test
    fun `error state returns error`() = runBlocking {
        val cryptoId = 1
        val sut = CryptoDetailsViewModel(mockCryptoRepository)

        every { mockCryptoRepository.getCrypto(cryptoId) } returns null

        sut.getCryptoDetails(cryptoId)

        sut.uiState.test {
            assertEquals(CryptoDetailsState.Error(Exception("Crypto not found")).toString(), awaitItem().toString())
        }
    }
}