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
import com.example.james.rms.CommonProfile.Language.LocalizationUtil;
import com.example.james.rms.CommonProfile.SharePreferences.MyPreferences;
import com.example.james.rms.CommonProfile.SharePreferences.PreferencesKey;
import com.example.james.rms.CommonProfile.Util.ObjectUtil;
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
import com.example.james.rms.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

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

    //MyPreferences
    private MyPreferences myPreferences;

    //Facebook
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    //Dao
    private UserProfileDao userProfileDao;
    private  FacebookDao facebookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //Facebook
        LoginManager.getInstance().logOut();
        //setup Dao
        userProfileDao = new UserProfileDaoImpl(this);
        facebookDao = new FacebookDaoImpl(this);
        //Preferences
        myPreferences = new MyPreferences(this, PreferencesKey.login_information);
        //
        btnCancel.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        //
        if( myPreferences.getPreferences_loginInformation() != null){
            saveLoginCheckBox.setChecked(true);
            String username = myPreferences.getPreferences_loginInformation().get("username");
            String password = myPreferences.getPreferences_loginInformation().get("password");
            String partyId  = myPreferences.getPreferences_loginInformation().get("partyId");
            setEditUsernameAndPassWord(username,password);
//            if(ObjectUtil.isNotNullEmpty(username) && ObjectUtil.isNotNullEmpty(password) && ObjectUtil.isNotNullEmpty(partyId)){
            if(ObjectUtil.isNotNullEmpty(partyId)){
                goToNavController();
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
                    Integer facebook_count = facebookDao.countFacebookId(request_json);
                    Log.d("asd", "userProfile [Response]: " + facebook_count);

                    if(facebook_count !=null){
                        myPreferences.clear();
                    }else{
                        new Exception("facebook_count  is Empty [Serve Response Error])");
                    }

                    if(facebook_count >=1){
                        UserProfile u = userProfileDao.findByFacebookId(request_json);
                        Log.d("asd","[Login]-[Facebook]-[findByFacebookId]-[Result]  :" + u.toString());
                        myPreferences.setPreferences_loginInformation(u);
                        Log.d("asd","partyId :" + myPreferences.getPreferences_loginInformation().get("partyId"));
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

                        UserProfile save_response = userProfileDao.save(userProfile_json);
                        Log.d("asd","[Login]-[Facebook]-[save]-[Result]  :" + save_response.toString());
                        myPreferences.setPreferences_loginInformation(save_response);
                        Toast.makeText(getApplicationContext(),"Create New Account",Toast.LENGTH_SHORT).show();
                    }
                    if(ObjectUtil.isNotNullEmpty(myPreferences.getPreferences_loginInformation().get("partyId"))) {
                        goToNavController();
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
//        ClassicDialog.showIndeterminate(this,R.color.blue0895ef,getString(R.string.loading),getString(R.string.waiting));
        switch (v.getId()){
            case R.id.btnCancel:
                List<UserProfile> loginModels = userProfileDao.findAll();
                if(loginModels != null)
                Toast.makeText(getApplicationContext(),loginModels.toString(),Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnLogin:

                if(!ObjectUtil.isNotNullEmpty(username) || !ObjectUtil.isNotNullEmpty(password)){
                    Toast.makeText(getApplicationContext(), R.string.loginValueIsNull, Toast.LENGTH_SHORT).show();
                    break;
                }
                if(!saveLoginCheckBox.isChecked()) {
                    myPreferences.clear();
                    setEditUsernameAndPassWord("", "");
                }
                String loginValue = UserProfileCombine.combine_usernameAndpassword(username, password);
                LoginModel loginModel = userProfileDao.checkLogin(loginValue);
                if (checkLoginStatus(loginModel)) {
                    myPreferences.setPreferences_loginInformation(loginModel);
                    goToNavController();
                }
                Toast.makeText(getApplicationContext(), loginModel.getLoginMessage(), Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ClassicDialog.showLeave(this,getString(R.string.leave));
    }

    public void setEditUsernameAndPassWord(String username , String password){
        editUsername.setText(username);
        editPassword.setText(password);
    }

    public Boolean checkLoginStatus(LoginModel loginModel){
        Boolean isSuccessful = false;
        if(loginModel != null && "LoginSuccessful".equals(loginModel.getLoginStatus())){
            isSuccessful = true;
        }
        return isSuccessful;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

    private void goToNavController(){
        LocalizationUtil.checkCurrentLocalization(this,PreferencesKey.localization);

        Toast.makeText(getApplicationContext(), getString(R.string.label_welcome),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, NavigationController.class);
        startActivity(intent);
    }
}
