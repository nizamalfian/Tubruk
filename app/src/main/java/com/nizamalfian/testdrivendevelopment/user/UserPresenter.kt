package com.nizamalfian.testdrivendevelopment.user

import com.nizamalfian.testdrivendevelopment.result.Result

class UserPresenter(private val view: UserLoginView,
                    private val repository: UserRepository) {
    fun login(username: String, password: String) {
        view.showLoading()
        when (repository.login(username, password)) {
            is Result.Success -> view.successLogin()
            is Result.Error -> view.failedLogin()
        }
        view.hideLoading()
    }
}
