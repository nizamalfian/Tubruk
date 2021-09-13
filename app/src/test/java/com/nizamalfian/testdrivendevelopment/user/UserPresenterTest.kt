package com.nizamalfian.testdrivendevelopment.user

import com.nizamalfian.testdrivendevelopment.dummy.Dummy
import com.nizamalfian.testdrivendevelopment.result.Result
import io.mockk.*
import org.junit.Before
import org.junit.Test

class UserPresenterTest {

    private val view = mockk<UserLoginView>()
    private val repository = mockk<UserRepository>()
    private lateinit var presenter: UserPresenter

    private val username = "nizam"
    private val password = "1234"

    @Before
    fun setUp() {
        presenter = UserPresenter(view, repository)
    }

    @Test
    fun `login - user is valid - show loading, success, hide loading`() {
        val expectedUser = Dummy.user

        every { view.showLoading() } just Runs
        every { repository.login(username, password) } returns Result.Success(expectedUser)
        every { view.successLogin() } just Runs
        every { view.hideLoading() } just Runs

        presenter.login(username, password)

        verifySequence {
            view.showLoading()
            repository.login(username, password)
            view.successLogin()
            view.hideLoading()
        }
        verify(atLeast = 0, atMost = 0) {
            view.failedLogin()
        }
    }

    @Test
    fun `login - user is invalid - show loading, failed, hide loading`() {
        every { view.showLoading() } just Runs
        every { repository.login(username, password) } returns Result.Error(404, Throwable("Invalid username and password"))
        every { view.failedLogin() } just Runs
        every { view.hideLoading() } just Runs

        presenter.login(username, password)

        verifySequence {
            view.showLoading()
            repository.login(username, password)
            view.failedLogin()
            view.hideLoading()
        }
        verify(atLeast = 0, atMost = 0) {
            view.successLogin()
        }
    }

    @Test
    fun `login - error response - show loading, failed, hide loading`() {
        every { view.showLoading() } just Runs
        every { repository.login(username, password) } returns Result.Error(500, Throwable("Internal server error"))
        every { view.failedLogin() } just Runs
        every { view.hideLoading() } just Runs

        presenter.login(username, password)

        verifySequence {
            view.showLoading()
            repository.login(username, password)
            view.failedLogin()
            view.hideLoading()
        }
        verify(atLeast = 0, atMost = 0) {
            view.successLogin()
        }
    }
}