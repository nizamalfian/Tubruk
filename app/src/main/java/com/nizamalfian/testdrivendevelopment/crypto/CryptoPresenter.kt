package com.nizamalfian.testdrivendevelopment.crypto

import com.nizamalfian.testdrivendevelopment.result.Result

class CryptoPresenter(private val view: CryptoView,
                      private val repository: CryptoRepository) {
    fun fetchCrypto() {
        view.showLoading()
        val crypto = repository.getCrypto()
        when(crypto) {
            is Result.Success -> {
                if (crypto.data?.isNotEmpty() == true)
                    view.showCrypto(crypto.data)
                else
                    view.showEmpty()
            }
            is Result.Error -> view.showError()
        }
        view.hideLoading()
    }
}