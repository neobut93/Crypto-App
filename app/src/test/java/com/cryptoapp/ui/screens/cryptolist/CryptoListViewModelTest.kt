package com.cryptoapp.ui.screens.cryptolist

import app.cash.turbine.test
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsState
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsViewModel
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

class CryptoListViewModelTest {

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
    fun `loading state return loading`() = runBlocking {
         val mockCryptoRepository = mockk<CryptoRepository>(relaxed = true)

        val sut = CryptoListViewModel(mockCryptoRepository)

        sut.uiState.test {
            assertEquals(CryptoListState.Loading, awaitItem())
        }
    }

    @Test
    fun testCase1() {
        val mockRepo = mockk<CryptoRepository>(relaxed = true)
        val expectedCrypto = runBlocking {
            mockRepo.fetchCryptos()
        }
        val sut = CryptoListViewModel(mockRepo)
        val result = sut.fetchCryptos()
        assertEquals(expectedCrypto, result)
    }
}