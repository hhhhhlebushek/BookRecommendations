package com.example.bookrecommendations;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.google.android.material.textfield.TextInputEditText;

public class LogIn extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;

    //TextView textViewSignUp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       /* editTextUsername = findViewById(R.id.loginUsername);
        editTextPassword = findViewById(R.id.loginPassword);
        progressBar = findViewById(R.id.loginProgress);*/
    }
    public void onClickReg(View v){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
    public void onClick(View v) {
        loginButton();
    }

    public void loginButton(){
        editTextUsername = findViewById(R.id.login);
        editTextPassword = findViewById(R.id.password);
        final String login, pass;
        login = String.valueOf(editTextUsername.getText());
        pass = String.valueOf(editTextPassword.getText());

        if (!login.equals("") && !pass.equals("")) {


            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {

                    String[] field = new String[2];
                    field[0] = "login";
                    field[1] = "pass";

                    String[] data = new String[2];
                    data[0] = login;
                    data[1] = pass;
                    PutData putData = new PutData("http://192.168.56.1/login/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {

                            String result = putData.getResult();

                            if (result.equals("Success")) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                                //save("1", login);
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                //save("0", "0");
                            }
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Не все обязательные поля заполнены", Toast.LENGTH_SHORT).show();
        }
    }
    public void save(String str, String login) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("check", str);
        myEdit.putString("loginInfo", login);
        myEdit.commit();
    }
}
