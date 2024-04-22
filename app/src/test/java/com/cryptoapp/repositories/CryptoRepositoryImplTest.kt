package com.cryptoapp.repositories

import com.cryptoapp.models.Crypto
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
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CryptoRepositoryImplTest {

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
    fun getCrypto() {
        // Arrange
        val mockRepo = mockk<CryptoRepository>()
        val sut: Crypto = mockk<Crypto>()

        every { mockRepo.getCrypto(1) } returns sut

        // Act
        val result = mockRepo.getCrypto(1)

        //Assert
        assertEquals(sut, result)
    }

    // need fix
    @Test
    fun fetchCryptos() {
        // Arrange
        val mockRepo = mockk<CryptoRepository>(relaxed = true)
        val sut: Crypto = mockk<Crypto>()

        // Act
        val result = runBlocking { mockRepo.fetchCryptos() }

        //Assert
        assertEquals(sut, result)
    }

}