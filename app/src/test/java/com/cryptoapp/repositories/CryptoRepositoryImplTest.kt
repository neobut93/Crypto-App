package com.cryptoapp.repositories

import app.cash.turbine.test
import com.cryptoapp.models.Crypto
import com.cryptoapp.network.CryptoService
import com.cryptoapp.sample.sampleCryptos
import com.cryptoapp.database.CryptoDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CryptoRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockService: CryptoService = mockk()
    private val mockCryptoDao = mockk<CryptoDao>(relaxed = true)

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
    fun `crypto repository returns success`() = runTest {
        val expectedCryptos: List<Crypto> = sampleCryptos

        val emptyCountryList = emptyList<Crypto>()

        coEvery { mockService.getAllCryptos() } returns Response.success(expectedCryptos)
        val sut = CryptoRepositoryImpl(mockService, mockCryptoDao)

        sut.cryptos.test {
            sut.fetchCryptos()
            assertEquals(emptyCountryList, awaitItem())
            assertEquals(expectedCryptos, awaitItem())
        }
    }


    @Test
    fun `crypto repository returns failure`() = runBlocking {

        coEvery { mockService.getAllCryptos() } returns Response.error(404, "{}".toResponseBody())
        val sut = CryptoRepositoryImpl(mockService, mockCryptoDao)

        try {
            sut.fetchCryptos()
        } catch (e: Throwable) {
            assertTrue(e.message.toString().contains("Request failed:"))
        }
    }
}
