package com.example.james.rms.ui.login

import android.content.Context
import com.example.james.rms.common.SharePreferences.MyPreferences
import com.example.james.rms.constant.PreferencesKey
import com.example.james.rms.core.model.Facebook
import com.example.james.rms.core.model.SettingModel
import com.example.james.rms.core.model.UserProfile

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.ui.login
 */
class LoginPresenter(context: Context, val view: LoginContract.View) : LoginContract.Presenter {

    //MyPreferences
    private var myPreferences: MyPreferences
    private var settingPreference: MyPreferences
    private var facebookPreference: MyPreferences
    init {
        view.setPresenter(this)
        myPreferences = MyPreferences(context, PreferencesKey.login_information)
        settingPreference = MyPreferences(context, PreferencesKey.setting)
        facebookPreference = MyPreferences(context, PreferencesKey.facebook)
    }


    override fun login(username: String, password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateServerStatus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setPreferences(userProfile: UserProfile?, settingModel: SettingModel?, facebook: Facebook?) {
        if (userProfile != null) myPreferences.setPreferences_loginInformation(userProfile)
        if (settingModel != null) settingPreference.setPreferences_Setting(settingModel)
        if (facebook != null) facebookPreference.setPreferences_facebook(facebook)
    }
    /**
     * Clear all 3 preference
     */
    private fun clearPreferences() {
        myPreferences.clear()
        facebookPreference.clear()
        settingPreference.clear()
    }
}