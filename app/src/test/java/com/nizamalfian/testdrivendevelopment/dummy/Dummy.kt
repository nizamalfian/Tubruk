package com.nizamalfian.testdrivendevelopment.dummy

import com.nizamalfian.testdrivendevelopment.crypto.CryptoModel
import com.nizamalfian.testdrivendevelopment.user.UserModel

object Dummy {
    val crypto: List<CryptoModel> = arrayListOf(
        CryptoModel("BTC", "USD 46,165.10"),
        CryptoModel("DOGE", "USD 0.241922"),
        CryptoModel("ETH", "USD 3,443.91"),
        CryptoModel("DASH", "USD 204.12"),
        CryptoModel("BNB", "USD 418.85"),
        CryptoModel("TRX", "USD 0.1141")
    )

    val user = UserModel("abcd", "Nizam")
}
