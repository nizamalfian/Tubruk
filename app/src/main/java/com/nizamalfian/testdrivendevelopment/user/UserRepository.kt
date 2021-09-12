package com.nizamalfian.testdrivendevelopment.user

import com.nizamalfian.testdrivendevelopment.result.Result

class UserRepository(private val localDataSource: UserLocalDataSource,
                     private val remoteDataSource: UserRemoteDataSource) {
    fun login(username: String, password: String): Result<UserModel> {
        return remoteDataSource.login(username, password).apply {
            if (this is Result.Success) {
                this.data?.let { localDataSource.saveUser(it) }
            }
        }
    }
}
