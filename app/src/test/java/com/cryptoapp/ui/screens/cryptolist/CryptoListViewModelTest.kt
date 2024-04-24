package com.cryptoapp.ui.screens.cryptolist

import app.cash.turbine.test
import com.cryptoapp.models.Crypto
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.sample.sampleCryptos
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CryptoListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockRepo = mockk<CryptoRepository>(relaxed = true)

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
    fun `crypto list view model returns loading`() = runBlocking {
        val sut = CryptoListViewModel(mockRepo)

        sut.uiState.test {
            assertEquals(CryptoListState.Loading, awaitItem())
        }
    }

    @Test
    fun `crypto list view model returns success`() = runBlocking {
        val expectedCryptos: List<Crypto> = sampleCryptos

        coEvery { mockRepo.cryptos} returns flowOf(expectedCryptos)

        val sut = CryptoListViewModel(mockRepo)
        sut.fetchCryptos()

        sut.uiState.test {
            // note: if you run the whole suite you would need to assert loading 1st, but works without if you run separately
            assertEquals(CryptoListState.Loading, awaitItem())
            assertEquals(CryptoListState.Success(expectedCryptos), awaitItem())
        }
    }

    @Test
    fun `crypto list view model returns error`() = runBlocking {
        coEvery { mockRepo.fetchCryptos()} throws RuntimeException("Error")

        val sut = CryptoListViewModel(mockRepo)
        sut.fetchCryptos()

        sut.uiState.test {
            assertEquals(CryptoListState.Loading, awaitItem())
            assertEquals(CryptoListState.Error(RuntimeException("Error")).toString(), awaitItem().toString())
        }
    }
}