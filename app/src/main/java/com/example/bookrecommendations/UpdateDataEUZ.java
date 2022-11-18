package com.example.bookrecommendations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class UpdateDataEUZ extends AppCompatActivity {
    EditText NewUserLogin,NewUserPass;

    //TextView textViewSignUp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_euz);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        NewUserLogin = findViewById(R.id.newLogin);
        NewUserPass = findViewById(R.id.newPass);
        Button UpdateLogin,UpdatePass;
        UpdateLogin = findViewById(R.id.buttonNewLogin);
        UpdatePass = findViewById(R.id.buttonNewPass);

        UpdateLogin.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));
        UpdatePass.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));


        UpdateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String login;

                login = String.valueOf(NewUserLogin.getText());

                if (!login.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "login";
                            field[1] = "id";

                            String[] data = new String[2];
                            data[0] = login;
                            data[1] = id;
                            PutData putData = new PutData("http://192.168.56.1/login/updateData.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Логин удачно обновлен!", Toast.LENGTH_SHORT).show();
                                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
                                        editor.commit();

                                        Intent i = new Intent(getApplicationContext(), LogIn.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    NewUserLogin.setText(null);
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Не все обязательные поля заполнены", Toast.LENGTH_SHORT).show();
                }
            }
        });

        UpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pass;

                pass = String.valueOf(NewUserPass.getText());

                if (!pass.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "pass";
                            field[1] = "id";

                            String[] data = new String[2];
                            data[0] = pass;
                            data[1] = id;
                            PutData putData = new PutData("http://192.168.56.1/login/updateData.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Пароль удачно обновлен!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    NewUserPass.setText(null);
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
