package com.nizamalfian.testdrivendevelopment.user

import com.nizamalfian.testdrivendevelopment.result.Result

interface UserRemoteDataSource {
    fun login(username: String, password: String): Result<UserModel>
}