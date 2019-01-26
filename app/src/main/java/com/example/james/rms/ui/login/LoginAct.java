package com.example.james.rms.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.james.rms.R;
import com.example.james.rms.common.DialogBox.ClassicDialog;
import com.example.james.rms.common.DialogBox.DialogModel;
import com.example.james.rms.common.Language.LocalizationUtil;
import com.example.james.rms.common.SharePreferences.MyPreferences;
import com.example.james.rms.common.util.ObjectUtil;
import com.example.james.rms.constant.PreferencesKey;
import com.example.james.rms.controller.NavigationAct;
import com.example.james.rms.core.combine.FacebookSearchCombine;
import com.example.james.rms.core.combine.UserProfileCombine;
import com.example.james.rms.core.dao.FacebookDao;
import com.example.james.rms.core.dao.FacebookDaoImpl;
import com.example.james.rms.core.dao.UserProfileDao;
import com.example.james.rms.core.dao.UserProfileDaoImpl;
import com.example.james.rms.core.model.Facebook;
import com.example.james.rms.core.model.LoginModel;
import com.example.james.rms.core.model.SettingModel;
import com.example.james.rms.core.model.Status;
import com.example.james.rms.core.model.UserProfile;
import com.example.james.rms.core.search_object.FacebookSearchObject;
import com.example.james.rms.network.ServeProfile;
import com.example.james.rms.service.LoginActivityService;
import com.example.james.rms.ui.register.RegisterAct;
import com.example.james.rms.ui.test_upload.TestUploadPhotoAct;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

public class LoginAct extends AppCompatActivity implements View.OnClickListener,LoginActivityService,LoginContract.View {

    private AppCompatActivity appCompatActivity;

    @BindView(R.id.login_image)
    de.hdodenhof.circleimageview.CircleImageView login_image;
    @BindView(R.id.edt_username)
    EditText editUsername;
    @BindView(R.id.edt_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.saveLoginCheckBox)
    android.support.v7.widget.AppCompatCheckBox saveLoginCheckBox;
    @BindView(R.id.facebooklogin)
    LoginButton fb_login;

    private LoginContract.Presenter presenter;
    //MyPreferences
    private MyPreferences myPreferences;
    private MyPreferences settingPreference;
    private MyPreferences facebookPreference;
    //Facebook
    private CallbackManager callbackManager;
    private List<String> permission = Arrays.asList("public_profile", "email", "user_birthday", "user_friends");

    //Service
    private LoginActivityService loginActivityService;
    //Dao
    private UserProfileDao userProfileDao;
    private FacebookDao facebookDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
        appCompatActivity = this;
        //Facebook
        LoginManager.getInstance().logOut();
        fb_login.setReadPermissions(permission);

        //set Sevice
        loginActivityService = (LoginActivityService) appCompatActivity;
        //setup Dao
        userProfileDao = new UserProfileDaoImpl(this);
        facebookDao = new FacebookDaoImpl(this);
        //Preferences
        myPreferences = new MyPreferences(this, PreferencesKey.INSTANCE.getLogin_information());
        settingPreference = new MyPreferences(this, PreferencesKey.INSTANCE.getSetting());
        facebookPreference = new MyPreferences(this, PreferencesKey.INSTANCE.getFacebook());
        //
        login_image.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        //
        loginActivityService.autoLogin();
        facebookLogin();


    }

    public void facebookLogin(){
        //Facebook
        callbackManager = CallbackManager.Factory.create();
        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //accessToken之後或許還會用到 先存起來
                final AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {

                            //當RESPONSE回來的時候

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                //讀出姓名 ID FB個人頁面連結
                                Log.d("fb","complete");
                                String facebook_userId = accessToken.getUserId();
                                String facebook_token = accessToken.getToken();

                                String age = object.optString("age_range");
                                String name = object.optString("name");
                                String email = object.optString("email");
                                String gender = object.optString("gender");
                                String birthday = object.optString("birthday");
                                String picture = object.optString("picture");

                                Log.d("fb","id:" + object.optString("id"));
                                Log.d("fb","name:" + name);
                                Log.d("fb","email:" + email);
                                Log.d("fb","gender:" + gender);
                                Log.d("fb","birthday:" + birthday);
                                Log.d("fb","picture:" + picture);
                                Log.d("fb","age_range:" + age);
                                Facebook facebook = new Facebook();
                                facebook.setFacebookId(facebook_userId);
                                facebook.setAge(age);
                                facebook.setName(name);
                                facebook.setEmail(email);
                                facebook.setBirthday(birthday);
                                facebook.setGender(gender);
                                facebook.setPicture(picture);

                                loginActivityService.fb_Login(facebook);
                            }
                        });
                //包入你想要得到的資料 送出request
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday,picture,age_range");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d("FB","loginResult (Cancel): ");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FB","loginResult (error): " + error);
                error.printStackTrace();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onLoginSuccess() {
        // TODO: 22/1/2019
    }

    @Override
    public void onLoginFail() {
        // TODO: 22/1/2019
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
//        ClassicDialog.showIndeterminate(this,R.color.blue0895ef,getString(R.string.loading),getString(R.string.waiting));
        switch (v.getId()){
            case R.id.login_image:
//                List<UserProfile> loginModels = userProfileDao.findAll();
//                if(loginModels != null)
//                Toast.makeText(getApplicationContext(),loginModels.toString(),Toast.LENGTH_SHORT).show();
                DialogModel dialogModel = new DialogModel();
                dialogModel.setContext(this);
                dialogModel.setTitle(getString(R.string.label_fill_server_address));
                dialogModel.setContent(ServeProfile.INSTANCE.getServe());
                ClassicDialog.showBasicInputBox(dialogModel);
                break;

            case R.id.btn_login:
                // do login in api
                loginActivityService.buttonLogin(username,password);
                break;
            case R.id.btn_register:
//                Intent intent = new Intent(this, RegisterAct.class);
                Intent intent = new Intent(this, TestUploadPhotoAct.class);
                startActivity(intent);
                break;
        }

    }


    @Override
    public void onBackPressed() {
        //show dialog to confirm leave
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
        LocalizationUtil.INSTANCE.checkCurrentLocalization(this, PreferencesKey.INSTANCE.getLocalization());

        Toast.makeText(getApplicationContext(), getString(R.string.label_welcome),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(LoginAct.this, NavigationAct.class);
        startActivity(intent);
    }

    @Override
    public void autoLogin() {
        // if set to remember username and pw , set it
        if( myPreferences.getPreferences_loginInformation() != null){
            saveLoginCheckBox.setChecked(true);
            String username = myPreferences.getPreferences_loginInformation().get("username");
            String password = myPreferences.getPreferences_loginInformation().get("password");
            String partyId  = myPreferences.getPreferences_loginInformation().get("partyId");
            setEditUsernameAndPassWord(username,password);

            String loginValue = UserProfileCombine.combine_partyId(partyId);
            UserProfile userProfile = userProfileDao.findByPartyId(loginValue);
            if(userProfile != null && ObjectUtil.isNotNullEmpty(userProfile.getPartyId())){
                if(ObjectUtil.isNotNullEmpty(partyId) && partyId.equalsIgnoreCase(userProfile.getPartyId())){
                    setPreferences(userProfile,userProfile.getSetting(),userProfile.getFacebook());
                }

                goToNavController();
            }

        }
    }

    @Override
    public void buttonLogin(String username, String password) {
        clearPreferences();
        if(!ObjectUtil.isNotNullEmpty(username) || !ObjectUtil.isNotNullEmpty(password)){
            Toast.makeText(getApplicationContext(), R.string.loginValueIsNull, Toast.LENGTH_SHORT).show();
            return;
        }
        if(!saveLoginCheckBox.isChecked()) {
            myPreferences.clear();
            setEditUsernameAndPassWord("", "");
        }
        String loginValue = UserProfileCombine.combine_usernameAndpassword(username, password);
        LoginModel loginModel = userProfileDao.checkLogin(loginValue);
        if (checkLoginStatus(loginModel)) {
            setPreferences(loginModel.getUserProfile(),loginModel.getUserProfile().getSetting(),loginModel.getUserProfile().getFacebook());
            if(loginModel != null && loginModel.getUserProfile()!=null && loginModel.getUserProfile().getSetting() != null)settingPreference.setPreferences_Setting(loginModel.getUserProfile().getSetting() );
            goToNavController();
        }
        if(loginModel != null)Toast.makeText(getApplicationContext(), loginModel.getLoginMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fb_Login(Facebook facebook) {
        clearPreferences();
        FacebookSearchObject facebookSearchObject = new FacebookSearchObject();
        facebookSearchObject.setFacebookId(facebook.getFacebookId());

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
                setPreferences(u,u.getSetting(),u.getFacebook());
                Log.d("asd","partyId :" + myPreferences.getPreferences_loginInformation().get("partyId"));
            } else if(facebook_count == 0) {
                LoginManager.getInstance().logInWithReadPermissions(
                        appCompatActivity,
                        permission);

//                Facebook facebook = new Facebook();
//                facebook.setFacebookId(facebook_userId);
                UserProfile userProfile = new UserProfile();
                userProfile.setStatus(Status.PROGRESS.name());
                userProfile.setCreateDate(new Date());
                userProfile.setCreateBy(facebook.getFacebookId()+".TL");
                userProfile.setPartyId(facebook.getFacebookId()+".TL");
                userProfile.setFacebook(facebook);

                SettingModel settingModel = new SettingModel();
                userProfile.setSetting(settingModel);

                UserProfileCombine userProfileCombine = new UserProfileCombine(UserProfile.class);
                String userProfile_json = userProfileCombine.modelToJson(userProfile);

                UserProfile save_response = userProfileDao.save(userProfile_json);
                Log.d("asd","[Login]-[Facebook]-[save]-[Result]  :" + save_response.toString());
                setPreferences(save_response,save_response.getSetting(),save_response.getFacebook());
                if(save_response.getSetting() != null)settingPreference.setPreferences_Setting(save_response.getSetting());
                Toast.makeText(getApplicationContext(),"Create New Account",Toast.LENGTH_SHORT).show();
            }
            if(ObjectUtil.isNotNullEmpty(myPreferences.getPreferences_loginInformation().get("partyId"))) {
                goToNavController();
            }else{
                LoginManager.getInstance().logOut();
                Toast.makeText(getApplicationContext(), getString(R.string.loginFail),Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onServerStatusReturn(boolean online) {

    }

    private void setPreferences(UserProfile userProfile, SettingModel settingModel, Facebook facebook){
        if(userProfile != null) myPreferences.setPreferences_loginInformation(userProfile);
        if(settingModel != null) settingPreference.setPreferences_Setting(settingModel);
        if(facebook !=null) facebookPreference.setPreferences_facebook(facebook);
    }

    private void clearPreferences(){
        myPreferences.clear();
        facebookPreference.clear();
        settingPreference.clear();
    }
}
