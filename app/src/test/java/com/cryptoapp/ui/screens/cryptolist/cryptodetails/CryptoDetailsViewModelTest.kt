package com.cryptoapp.ui.screens.cryptolist.cryptodetails

import com.cryptoapp.MockRepository
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.ui.screens.cryptodetails.CryptoDetailsViewModel
import com.cryptoapp.ui.screens.cryptolist.CryptoListViewModel
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
import org.junit.Assert
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
        // Arrange
        val mockRepo = mockk<CryptoRepository>(relaxed = true)
        val expectedCrypto = mockRepo.getCrypto(1)
        val sut = CryptoDetailsViewModel(mockRepo)
        every { mockRepo.getCrypto(1) } returns expectedCrypto

        // Act
        val result = sut.getCryptoDetails(1)

        //Assert
        Assert.assertEquals(expectedCrypto, result)
    }
}