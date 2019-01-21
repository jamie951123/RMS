package com.example.james.rms.ui.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.james.rms.Injection
import com.example.james.rms.R
import com.example.james.rms.util.bind

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.login
 */
class RegisterAct :AppCompatActivity() ,RegisterContract.View{

    @JvmField
    var presenter:RegisterContract.Presenter? = null

    private val edt_username by bind<EditText>(R.id.edt_username)
    private val edt_pw by bind<EditText>(R.id.edt_password)
    private val btn_register by bind<Button>(R.id.btn_register)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_register)

        presenter = RegisterPresenter(
                Injection.provideLoginRepository(),this)


        btn_register.setOnClickListener {
            presenter?.registerAccount(edt_username.text.toString(),edt_pw.text.toString())
        }

    }

    override fun showLoadingDialog() {
        edt_username.isEnabled = false
        edt_pw.isEnabled = false
    }

    override fun hideLoadingDialog() {
        edt_username.isEnabled = true
        edt_pw.isEnabled = true
    }

    override fun onRegisterAccountSuccess(username: String, password: String) {
        Toast.makeText(this,"REGISTER SUCCESS!!",Toast.LENGTH_SHORT).show()
    }

    override fun onRegisterAccountFail(reason: String) {
        Toast.makeText(this,reason,Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: RegisterContract.Presenter) {
        this.presenter = presenter
    }
}