package com.nizamalfian.testdrivendevelopment.crypto

import com.nizamalfian.testdrivendevelopment.result.Result

class CryptoRepository(private val remoteDataSource: CryptoRemoteDataSource) {
    fun getCrypto(): Result<List<CryptoModel>> {
        return remoteDataSource.getCrypto()
    }
}
