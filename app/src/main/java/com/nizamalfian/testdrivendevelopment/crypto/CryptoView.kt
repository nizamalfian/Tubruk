package com.nizamalfian.testdrivendevelopment.crypto

interface CryptoView {
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun showEmpty()
    fun showCrypto(crypto: List<CryptoModel>)
}


