package com.example.james.rms.ui.register

import com.example.james.rms.data.LoginDataSource
import com.example.james.rms.data.repo.LoginRepository

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.register
 */
class RegisterPresenter(
        private val userLoginRepository: LoginRepository,
        private val view: RegisterContract.View):RegisterContract.Presenter {




    override fun registerAccount(username: String, password: String) {
        view.showLoadingDialog()
        userLoginRepository.registerAccount(username,password,object : LoginDataSource.Callback{
            override fun onSuccess() {
                view.onRegisterAccountSuccess(username, password)
                view.hideLoadingDialog()
            }

            override fun onFail(reason: String) {
                view.onRegisterAccountFail(reason)
                view.hideLoadingDialog()
            }
        })
    }
}