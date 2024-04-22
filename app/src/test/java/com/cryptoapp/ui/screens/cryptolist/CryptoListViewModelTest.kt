package com.cryptoapp.ui.screens.cryptolist

import com.cryptoapp.models.Crypto
import com.cryptoapp.repositories.CryptoRepository
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
    fun testCase1() {
        // Arrange
        val mockRepo = mockk<CryptoRepository>(relaxed = true)
        val expectedCrypto = runBlocking {
            mockRepo.fetchCryptos()
        }
        val sut = CryptoListViewModel(mockRepo)

        // Act
        val result = sut.fetchCryptos()

        //Assert
        assertEquals(expectedCrypto, result)
    }
}