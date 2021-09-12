package com.nizamalfian.testdrivendevelopment.result

sealed class Result<out T>(val data: T?) {

    data class Success<out T>(val t: T): Result<T>(t)

    data class Error(val errorCode: Int, val throwable: Throwable? = null): Result<Nothing>(null)

}
