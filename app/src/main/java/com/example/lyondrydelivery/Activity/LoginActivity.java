package com.example.lyondrydelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.Login_data;
import com.example.lyondrydelivery.Modal.Login_responce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lyondrydelivery.R.id.et_userid;

public class LoginActivity extends AppCompatActivity {
    Button Btn_login;
    EditText UserId, Password;
    String USERID, PASSWORD;

    AwesomeValidation awesomeValidation;
    SharedPrefManager sharedPrefManager;
    String Token;
    Login_responce_modal loginResponceModal;
    Login_data login_data;
    String userName,userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Btn_login = findViewById(R.id.btn_login);
        UserId = findViewById(et_userid);
        Password = findViewById(R.id.et_pass);
        Password.setTransformationMethod(PasswordTransformationMethod.getInstance());

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        UserLogin();
    }


    private void UserLogin() {
        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERID = UserId.getText().toString().trim();
                PASSWORD = Password.getText().toString().trim();
                if (USERID.isEmpty()) {
                    UserId.setError("User id can't be blank");
                    UserId.requestFocus();
                    return;
                } else if (PASSWORD.isEmpty()) {
                    Password.setError("Password can't be blank");
                    Password.findFocus();
                    return;
                } else if (USERID.length() <= 0 || PASSWORD.length() <= 0) {
                    Toast.makeText(LoginActivity.this, "Faild", Toast.LENGTH_SHORT).show();
                } else {

                    Validations();

                }

            }
        });

    }

    private void Validations() {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, et_userid, RegexTemplate.NOT_EMPTY, R.string.invaled_userid);
        //awesomeValidation.addValidation(this, et_userid,"[5]$",R.string.invaled_userid);
        String regexPassword = "[a-zA-Z0-9\\!\\@\\#\\$]{3,24}";
        awesomeValidation.addValidation(this, R.id.et_pass, regexPassword, R.string.invalid_password);

        if (awesomeValidation.validate()) {

            Call<Login_responce_modal> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .login(USERID, PASSWORD, 0);

            call.enqueue(new Callback<Login_responce_modal>() {
                @Override
                public void onResponse(@NotNull Call<Login_responce_modal> call, Response<Login_responce_modal> response) {

                    Log.i("responce", "***************" + response);

                    if (response.isSuccessful()) {

                        loginResponceModal = response.body();

                        if (loginResponceModal.getSuccess()) {
                            Token = loginResponceModal.getHttpResponseHeader();
                            Log.i("Token", "***********Token***********************" + Token);
                            login_data = loginResponceModal.getData();

                            userName = login_data.getUserName();
                            userPassword = login_data.getUserPassword();
                            boolean active = login_data.getIsActive();

                            sharedPrefManager.saveUserLogin(login_data);
                            sharedPrefManager.saveToken(loginResponceModal);

                            if (active==false){
                                Toast.makeText(LoginActivity.this, "UserName and Password is wrong", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("Token", Token);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                            }


                        }
                    } else {
                        Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Login_responce_modal> call, Throwable t) {

                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(LoginActivity.this, "Validated Faild", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPrefManager.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }
        Log.i("isLoggedIn","********isLoggedIn***********"+sharedPrefManager.isLoggedIn());
//        else {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
    }
}

