package com.nizamalfian.testdrivendevelopment.user

interface UserLocalDataSource {
    fun saveUser(user: UserModel)
}