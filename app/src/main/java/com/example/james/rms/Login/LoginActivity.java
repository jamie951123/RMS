package com.example.james.rms.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.LoginPreferences;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Model.LoginModel;
import com.example.james.rms.Core.Model.UserProfile;
import com.example.james.rms.Login.Service.LoginService;
import com.example.james.rms.Login.Service.LoginServiceImpl;
import com.example.james.rms.R;

import java.util.List;

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

    LoginPreferences loginPreferences ;
    //
    private LoginCombine loginCombine;
    //dialog
    ClassicDialog classicDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPreferences = new LoginPreferences(this,"loginInformation", MODE_PRIVATE);
        classicDialog = new ClassicDialog(this);
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
        loginCombine = new LoginCombine();
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        LoginService loginService = new LoginServiceImpl();
        switch (v.getId()){
            case R.id.btnCancel:
                List<UserProfile> loginModels = loginService.findAll();
                Toast.makeText(getApplicationContext(),loginModels.toString(),Toast.LENGTH_SHORT).show();
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
                    classicDialog.showIndeterminate(R.color.blue0895ef,getString(R.string.loading),getString(R.string.waiting));
                    String loginValue = loginCombine.combine_loginValue(username, password);
                    loginService.checkLogin(loginValue);
                    LoginModel loginModel = loginService.checkLogin(loginValue);
                    if (checkLoginStatus(loginModel)) {
                        loginPreferences.setPreferences_loginInformation(loginModel);
                        Intent intent = new Intent();
                        intent.setClass(this, NavigationController.class);
                        startActivity(intent);
                    }
                    Toast.makeText(getApplicationContext(), loginModel.getLoginMessage(), Toast.LENGTH_SHORT).show();
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
        if(getApplicationContext().getResources().getString(R.string.loginSuccessful).equals(loginModel.getLoginStatus())){
            isSuccessful = true;
        }
        return isSuccessful;
    }
}
