package com.nizamalfian.testdrivendevelopment.user

import com.nhaarman.mockitokotlin2.anyOrNull
import com.nizamalfian.testdrivendevelopment.dummy.Dummy
import com.nizamalfian.testdrivendevelopment.result.Result
import io.mockk.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals as assertEquals

class UserRepositoryTest {

    private val localDataSource = mockk<UserLocalDataSource>()
    private val remoteDataSource = mockk<UserRemoteDataSource>()
    private lateinit var repository: UserRepository

    private val username = "nizam"
    private val password = "1234"

    @Before
    fun setUp() {
        repository = UserRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `login - valid - save local, success`() {
        val expectedUser = Dummy.user
        val expectedLoginResult = Result.Success(expectedUser)

        every { remoteDataSource.login(username, password) } returns expectedLoginResult
        every { localDataSource.saveUser(expectedUser) } just Runs

        val actualLoginResult = repository.login(username, password)

        verify(atLeast = 1, atMost = 1) { localDataSource.saveUser(expectedUser) }
        verify(atLeast = 1, atMost = 1) { remoteDataSource.login(username, password) }
        assertEquals(expectedLoginResult, actualLoginResult)
    }

    @Test
    fun `login - invalid - no save local, failed`() {
        val expectedLoginResult = Result.Error(401, Throwable("invalid username and password"))

        every { remoteDataSource.login(username, password) } returns expectedLoginResult

        val actualLoginResult = repository.login(username, password)

        verify(atLeast = 0, atMost = 0) { localDataSource.saveUser(anyOrNull()) }
        verify(atLeast = 1, atMost = 1) { remoteDataSource.login(username, password) }
        assertEquals(expectedLoginResult, actualLoginResult)
    }
}
