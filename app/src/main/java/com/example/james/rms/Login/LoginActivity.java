package com.example.james.rms.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.james.rms.CommonProfile.DialogBox.ClassicDialog;
import com.example.james.rms.CommonProfile.ObjectUtil;
import com.example.james.rms.CommonProfile.SharePreferences.LoginPreferences;
import com.example.james.rms.Controller.NavigationController;
import com.example.james.rms.Core.Combine.FacebookSearchCombine;
import com.example.james.rms.Core.Combine.UserProfileCombine;
import com.example.james.rms.Core.Dao.FacebookDao;
import com.example.james.rms.Core.Dao.FacebookDaoImpl;
import com.example.james.rms.Core.Dao.UserProfileDao;
import com.example.james.rms.Core.Dao.UserProfileDaoImpl;
import com.example.james.rms.Core.Model.Facebook;
import com.example.james.rms.Core.Model.LoginModel;
import com.example.james.rms.Core.Model.Status;
import com.example.james.rms.Core.Model.UserProfile;
import com.example.james.rms.Core.SearchObject.FacebookSearchObject;
import com.example.james.rms.Login.Service.LoginService;
import com.example.james.rms.Login.Service.LoginServiceImpl;
import com.example.james.rms.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Date;
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
    @BindView(R.id.facebooklogin)
    LoginButton login;

    private LoginPreferences loginPreferences ;

    //Facebook
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    //dialog
    ClassicDialog classicDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
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
//            if(ObjectUtil.isNotNullEmpty(username) && ObjectUtil.isNotNullEmpty(password) && ObjectUtil.isNotNullEmpty(partyId)){
            if(ObjectUtil.isNotNullEmpty(partyId)){
                Intent intent = new Intent();
                intent.setClass(this, NavigationController.class);
                startActivity(intent);
            }
        }
        FacebookLogin();
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    public void FacebookLogin(){
        //Facebook
        callbackManager = CallbackManager.Factory.create();
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //accessToken之後或許還會用到 先存起來
                accessToken = loginResult.getAccessToken();
//                GraphRequest request = GraphRequest.newMeRequest(
//                        accessToken,
//                        new GraphRequest.GraphJSONObjectCallback() {
//
//                            //當RESPONSE回來的時候
//
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//
//                                //讀出姓名 ID FB個人頁面連結
//
//                                Log.d("FB","complete");
//                                Log.d("FB",object.optString("name"));
//                                Log.d("FB",object.optString("link"));
//                                Log.d("FB",object.optString("id"));
//
//                            }
//                        });
//                //包入你想要得到的資料 送出request
//
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,link");
//                request.setParameters(parameters);
//                request.executeAsync();
                String facebook_userId = accessToken.getUserId();
                String facebook_token = accessToken.getToken();
                Log.d("asd","loginResult (Successful)-[UserId]: " + facebook_userId);
                Log.d("asd","loginResult (Successful)-[Token]: " + facebook_token);

                FacebookSearchObject facebookSearchObject = new FacebookSearchObject();
                facebookSearchObject.setFacebookId(facebook_userId);

                try {
                    FacebookSearchCombine facebookSearchCombine = new FacebookSearchCombine(FacebookSearchObject.class);
                    String request_json = facebookSearchCombine.modelToJson(facebookSearchObject);
                    FacebookDao facebookDao = new FacebookDaoImpl();
                    Integer facebook_count = facebookDao.countFacebookId(request_json);
                    Log.d("asd", "userProfile [Response]: " + facebook_count);

                    if(facebook_count !=null){
                        loginPreferences.clear_loginInformation();
                    }else{
                        new Exception("facebook_count  is Empty [Serve Response Error])");
                    }

                    if(facebook_count >=1){
                        UserProfileDao UserProfileDao = new UserProfileDaoImpl();
                        UserProfile u = UserProfileDao.findByFacebookId(request_json);
                        Log.d("asd","[Login]-[Facebook]-[findByFacebookId]-[Result]  :" + u.toString());
                        loginPreferences.setPreferences_loginInformation(u);
                        Log.d("asd","partyId :" + loginPreferences.getPreferences_loginInformation().get("partyId"));
                        Toast.makeText(getApplicationContext(),loginPreferences.getPreferences_loginInformation().get("partyId"),Toast.LENGTH_SHORT).show();
                    } else if(facebook_count == 0) {
                        Facebook facebook = new Facebook();
                        facebook.setFacebookId(facebook_userId);
                        UserProfile userProfile = new UserProfile();
                        userProfile.setStatus(Status.PROGRESS.name());
                        userProfile.setCreateDate(new Date());
                        userProfile.setCreateBy(facebook_userId+".TL");
                        userProfile.setPartyId(facebook_userId+".TL");
                        userProfile.setFacebook(facebook);

                        UserProfileCombine userProfileCombine = new UserProfileCombine(UserProfile.class);
                        String userProfile_json = userProfileCombine.modelToJson(userProfile);

                        UserProfileDao userProfileDao = new UserProfileDaoImpl();
                        UserProfile save_response = userProfileDao.save(userProfile_json);
                        Log.d("asd","[Login]-[Facebook]-[save]-[Result]  :" + save_response.toString());
                        loginPreferences.setPreferences_loginInformation(save_response);
                        Toast.makeText(getApplicationContext(),"Create New Account",Toast.LENGTH_SHORT).show();
                    }
                    if(ObjectUtil.isNotNullEmpty(loginPreferences.getPreferences_loginInformation().get("partyId"))) {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, NavigationController.class);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                Log.d("asd","loginResult (Cancel): ");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("asd","loginResult (error): " + error);
                error.printStackTrace();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View v) {
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
                    String loginValue = UserProfileCombine.combine_usernameAndpassword(username, password);
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
