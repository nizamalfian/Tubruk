package com.nizamalfian.testdrivendevelopment.crypto

import com.nizamalfian.testdrivendevelopment.dummy.Dummy
import com.nizamalfian.testdrivendevelopment.result.Result
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals as assertEquals

class CryptoRepositoryTest {
    private val remoteDataSource = mockk<CryptoRemoteDataSource>()
    private lateinit var repository: CryptoRepository

    @Before
    fun setup() {
        repository = CryptoRepository(remoteDataSource)
    }

    @Test
    fun `get crypto - data available - success`() {
        val expectedData = Result.Success(Dummy.crypto)
        every {
            remoteDataSource.getCrypto()
        } returns expectedData

        val actualData: Result<List<CryptoModel>> = repository.getCrypto()

        verify { remoteDataSource.getCrypto() }
        assertEquals(expectedData, actualData)
    }

    @Test
    fun `get crypto - empty data - success`() {
        val expectedData = Result.Success(listOf<CryptoModel>())
        every {
            remoteDataSource.getCrypto()
        } returns expectedData

        val actualData: Result<List<CryptoModel>> = repository.getCrypto()

        verify { remoteDataSource.getCrypto() }
        assertEquals(expectedData, actualData)
    }

    @Test
    fun `get crypto - 404 error - failed`() {
        val expectedData = Result.Error(404, Throwable(""))
        every {
            remoteDataSource.getCrypto()
        } returns expectedData

        val actualData: Result<List<CryptoModel>> = repository.getCrypto()

        verify { remoteDataSource.getCrypto() }
        assertEquals(expectedData, actualData)
    }

    @Test
    fun `get crypto - 500 error - failed`() {
        val expectedData = Result.Error(500, Throwable(""))
        every {
            remoteDataSource.getCrypto()
        } returns expectedData

        val actualData: Result<List<CryptoModel>> = repository.getCrypto()

        verify { remoteDataSource.getCrypto() }
        assertEquals(expectedData, actualData)
    }
}
