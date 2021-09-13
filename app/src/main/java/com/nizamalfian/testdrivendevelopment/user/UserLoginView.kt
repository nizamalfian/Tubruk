package com.nizamalfian.testdrivendevelopment.user

interface UserLoginView {
    fun showLoading()
    fun hideLoading()
    fun failedLogin()
    fun successLogin()
}
