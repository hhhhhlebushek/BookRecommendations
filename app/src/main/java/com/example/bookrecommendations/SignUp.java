package com.example.bookrecommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class SignUp extends AppCompatActivity {
    EditText editTextUsername, editTextFullname, editTextEmail, editTextPassword, editTextPhone, editTextLogin;

    public void onClickAuto(View v) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUsername = findViewById(R.id.lastname);
        editTextPassword = findViewById(R.id.password);
        editTextEmail = findViewById(R.id.email);
        editTextPhone = findViewById(R.id.phone);
        editTextLogin = findViewById(R.id.login);
        Button buttonSignUp;
        editTextFullname = findViewById(R.id.firstname);




    /*public void onClickReg(View v) {
        signUpButton();
    }*/
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUsername = findViewById(R.id.lastname);
                editTextPassword = findViewById(R.id.password);
                editTextEmail = findViewById(R.id.email);
                editTextFullname = findViewById(R.id.firstname);
                editTextPhone = findViewById(R.id.phone);
                editTextLogin = findViewById(R.id.login);

                //progressBar = findViewById(R.id.progress);
                final String lastName, firstName, email, pass, login, numberphone;

                lastName = String.valueOf(editTextUsername.getText());
                firstName = String.valueOf(editTextFullname.getText());
                email = String.valueOf(editTextEmail.getText());
                pass = String.valueOf(editTextPassword.getText());
                login = String.valueOf(editTextLogin.getText());
                numberphone = String.valueOf(editTextPhone.getText());
                if (pass.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Длина пароля не менее 8 символов", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!lastName.equals("") && !firstName.equals("") && !login.equals("") && !pass.equals("") && pass.length() >= 8) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[6];
                            field[0] = "lastName";
                            field[1] = "firstName";
                            field[2] = "numberphone";
                            field[3] = "email";
                            field[4] = "login";
                            field[5] = "pass";

                            String[] data = new String[6];
                            data[0] = lastName;
                            data[1] = firstName;
                            data[2] = numberphone;
                            data[3] = email;
                            data[4] = login;
                            data[5] = pass;
                            PutData putData = new PutData("http://192.168.56.1/login/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        onClickAuto(null);
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Не все обязательные поля заполнены", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}