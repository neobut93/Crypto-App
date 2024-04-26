package com.cryptoapp.ui.screens.cryptodetails

import app.cash.turbine.test
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.sample.sampleCrypto
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CryptoDetailsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `crypto details view model returns loading`() = runTest {
        val mockCryptoRepository = mockk<CryptoRepository>(relaxed = true)
        val sut = CryptoDetailsViewModel(mockCryptoRepository)

        sut.uiState.test {
            assertEquals(CryptoDetailsState.Loading, awaitItem())
        }
    }

    @Test
    fun `crypto details view model returns success`() = runTest {
        val mockCryptoRepository = mockk<CryptoRepository>(relaxed = true)
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
    fun `crypto details view model returns error`() = runTest {
        val mockCryptoRepository = mockk<CryptoRepository>(relaxed = true)
        val cryptoId = 1
        val sut = CryptoDetailsViewModel(mockCryptoRepository)

        every { mockCryptoRepository.getCrypto(cryptoId) } returns null

        sut.getCryptoDetails(cryptoId)

        sut.uiState.test {
            assertEquals(CryptoDetailsState.Error(Exception("Crypto not found")).toString(), awaitItem().toString())
        }
    }
}