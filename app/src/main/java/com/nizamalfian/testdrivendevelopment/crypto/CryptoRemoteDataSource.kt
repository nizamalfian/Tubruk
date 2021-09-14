package com.nizamalfian.testdrivendevelopment.crypto

import com.nizamalfian.testdrivendevelopment.result.Result

interface CryptoRemoteDataSource {

    fun getCrypto(): Result<List<CryptoModel>>

}
