package com.example.james.rms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.SharePreferences.LoginPreferences;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Login.LoginCombine;
import com.example.james.rms.Login.LoginPHPPath;
import com.example.james.rms.Login.LoginSplit;
import com.example.james.rms.Login.Model.LoginModel;
import com.example.james.rms.NetWork.HttpGetAsyncService;
import com.example.james.rms.Login.LoginHttpPostService;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.editUsername)
    EditText editUsername;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.saveLoginCheckBox)
    android.support.v7.widget.AppCompatCheckBox saveLoginCheckBox;

    private HttpGetAsyncService httpGetAsyncService = new HttpGetAsyncService();
    private LoginHttpPostService loginHttpPostService = new LoginHttpPostService();
    LoginPreferences loginPreferences = new LoginPreferences(this,"loginInformation", MODE_PRIVATE);
    //
    private LoginSplit loginSplit;
    private LoginCombine loginCombine;
    //dialog
    ClassicDialog classicDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        classicDialog = new ClassicDialog(this,R.color.blue0895ef,getString(R.string.loading),getString(R.string.waiting));
        btnCancel.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        if( loginPreferences.getPreferences_loginInformation() != null){
            saveLoginCheckBox.setChecked(true);
            String username = loginPreferences.getPreferences_loginInformation().get("username");
            String password = loginPreferences.getPreferences_loginInformation().get("password");
            String partyId  = loginPreferences.getPreferences_loginInformation().get("partyId");
            setEditUsernameAndPassWord(username,password);
            if(ObjectUtil.isNotNullEmpty(username) && ObjectUtil.isNotNullEmpty(password) && ObjectUtil.isNotNullEmpty(partyId)){
                Intent intent = new Intent();
                intent.setClass(this, NavigationController.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View v) {
        loginSplit = new LoginSplit();
        loginCombine = new LoginCombine();
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        switch (v.getId()){

            case R.id.btnCancel:
                String url_userProfile = LoginPHPPath.php_userProfile();
                String userProfile_result ="";
                userProfile_result = httpGetAsyncService.findAllFromUserProfile(url_userProfile);
                Toast.makeText(getApplicationContext(),userProfile_result,Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnLogin:
                if(!ObjectUtil.isNotNullEmpty(username) || !ObjectUtil.isNotNullEmpty(password)){
                    Toast.makeText(getApplicationContext(), R.string.loginValueIsNull, Toast.LENGTH_SHORT).show();
                    break;
                }
                if(!saveLoginCheckBox.isChecked()) {
                    loginPreferences.clear_loginInformation();
                    setEditUsernameAndPassWord("", "");
                }
                    classicDialog.showIndeterminate();
                    String url_checkLogin = LoginPHPPath.php_checkLogin();
                    String loginValue = loginCombine.combine_loginValue(username, password);
                    String login_Result = loginHttpPostService.checkLogin(url_checkLogin, loginValue);
                    LoginModel loginModel = loginSplit.conventLoginStatus(login_Result);
                    String loginMessage = loginModel.getLoginMessage();
                    if (checkLoginStatus(loginModel)) {
                        loginPreferences.setPreferences_loginInformation(loginModel);
                        Intent intent = new Intent();
                        intent.setClass(this, NavigationController.class);
                        startActivity(intent);
                    }
                    Toast.makeText(getApplicationContext(), loginMessage, Toast.LENGTH_SHORT).show();
                    classicDialog.dismiss();
                break;
        }

    }

    public void setEditUsernameAndPassWord(String username , String password){
        editUsername.setText(username);
        editPassword.setText(password);
    }

    public Boolean checkLoginStatus(LoginModel loginModel){
        Boolean isSuccessful = false;
        if(getApplicationContext().getResources().getString(R.string.loginSuccessful).equals(loginModel.getLoginMessage())){
            isSuccessful = true;
        }
        return isSuccessful;
    }
}
