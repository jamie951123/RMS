package com.example.james.rms.ui.register

import mvp.BasePresenter
import mvp.BaseView

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.login
 */
interface RegisterContract {

    interface Presenter:BasePresenter{

        fun registerAccount(username:String,password:String)


    }

    interface View:BaseView<Presenter>{

        fun onRegisterAccountSuccess(username: String, password: String)

        fun onRegisterAccountFail(reason:String)

        fun showLoadingDialog()
        fun hideLoadingDialog()
    }
}