package com.example.james.rms.ui.login

import mvp.BasePresenter
import mvp.BaseView

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.login
 */
interface LoginContract {

    interface Presenter:BasePresenter{

        fun login(username:String,password:String)
    }

    interface View:BaseView<Presenter>{

        fun onLoginSuccess()

        fun onLoginFail()
    }
}