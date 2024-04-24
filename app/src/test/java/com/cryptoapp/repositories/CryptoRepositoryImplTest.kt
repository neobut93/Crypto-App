package com.cryptoapp.repositories

import app.cash.turbine.test
import com.cryptoapp.models.Crypto
import com.cryptoapp.network.CryptoService
import com.cryptoapp.sample.sampleCryptos
import com.kodeco.android.countryinfo.database.CryptoDao
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

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
        val mockRepo = mockk<CryptoRepositoryImpl>()
        val expectedCrypto: Crypto = mockk<Crypto>()

        every { mockRepo.getCrypto(1) } returns expectedCrypto

        // Act
        val sut = mockRepo.getCrypto(1)

        //Assert
        assertEquals(expectedCrypto, sut)
    }


    @Test
    fun fetchCryptos() = runTest {
        val expectedCountries: List<Crypto> = sampleCryptos
        val mockService: CryptoService = mockk()
        val mockCountryDao = mockk<CryptoDao>(relaxed = true)
        val emptyCountryList = emptyList<Crypto>()

        coEvery { mockService.getAllCryptos() } returns Response.success(expectedCountries)
        val sut = CryptoRepositoryImpl(mockService, mockCountryDao)

        sut.cryptos.test {
            sut.fetchCryptos()
            assertEquals(emptyCountryList, awaitItem())
            assertEquals(expectedCountries, awaitItem())
        }
    }
}