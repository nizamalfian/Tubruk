package com.nizamalfian.testdrivendevelopment.crypto

import com.nhaarman.mockitokotlin2.anyOrNull
import com.nizamalfian.testdrivendevelopment.dummy.Dummy
import com.nizamalfian.testdrivendevelopment.result.Result
import io.mockk.*
import org.junit.Before
import org.junit.Test

class CryptoPresenterTest {
    private val view = mockk<CryptoView>()
    private val repository = mockk<CryptoRepository>()

    private lateinit var presenter: CryptoPresenter

    @Before
    fun setUp() {
        presenter = CryptoPresenter(view, repository)
    }

    @Test
    fun `fetch crypto - data available - show loading, fetch, show data, hide loading`() {
        val expectedData = Dummy.crypto

        every { view.showLoading() } just Runs
        every { repository.getCrypto() } returns Result.Success(expectedData)
        every { view.showCrypto(expectedData) } just Runs
        every { view.hideLoading() } just Runs

        presenter.fetchCrypto()

        verify(atLeast = 1, atMost = 1) { view.showLoading() }
        verify(atLeast = 1, atMost = 1) { repository.getCrypto() }
        verify(atLeast = 1, atMost = 1) { view.showCrypto(expectedData) }
        verify(atLeast = 1, atMost = 1) { view.hideLoading() }
        verify(atLeast = 0, atMost = 0) { view.showError() }
        verify(atLeast = 0, atMost = 0) { view.showEmpty() }
    }

    @Test
    fun `fetch crypto - data empty - show loading, fetch, show empty, hide loading`() {
        val expectedData = listOf<CryptoModel>()

        every { view.showLoading() } just Runs
        every { repository.getCrypto() } returns Result.Success(expectedData)
        every { view.showEmpty() } just Runs
        every { view.hideLoading() } just Runs

        presenter.fetchCrypto()

        verify(atLeast = 1, atMost = 1) { view.showLoading() }
        verify(atLeast = 1, atMost = 1) { repository.getCrypto() }
        verify(atLeast = 0, atMost = 0) { view.showCrypto(expectedData) }
        verify(atLeast = 1, atMost = 1) { view.hideLoading() }
        verify(atLeast = 0, atMost = 0) { view.showError() }
        verify(atLeast = 1, atMost = 1) { view.showEmpty() }
    }

    @Test
    fun `fetch crypto - data error - show loading, fetch, show error, hide loading`() {
        every { view.showLoading() } just Runs
        every { repository.getCrypto() } returns Result.Error(400, Throwable())
        every { view.showError() } just Runs
        every { view.hideLoading() } just Runs

        presenter.fetchCrypto()

        verify(atLeast = 1, atMost = 1) { view.showLoading() }
        verify(atLeast = 1, atMost = 1) { repository.getCrypto() }
        verify(atLeast = 0, atMost = 0) { view.showCrypto(anyOrNull()) }
        verify(atLeast = 1, atMost = 1) { view.hideLoading() }
        verify(atLeast = 1, atMost = 1) { view.showError() }
        verify(atLeast = 0, atMost = 0) { view.showEmpty() }
    }
}
