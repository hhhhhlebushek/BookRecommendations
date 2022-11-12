package com.example.bookrecommendations;

import android.annotation.SuppressLint;
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


public class UpdatePersonalData extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    EditText NewUsername;

    //TextView textViewSignUp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_data);

        NewUsername = findViewById(R.id.newName);
        Button UpdateName;
        UpdateName = findViewById(R.id.buttonNewName);
        UpdateName.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));
        UpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                final String name, firstName, email, pass, login, numberphone;

                name = String.valueOf(NewUsername.getText());

                if (!name.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String field, data;
                            field = "firstName";
                            data = name;
                            PutDataNew putData = new PutDataNew("http://192.168.56.1/login/updateName.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Имя удачно обновлено!", Toast.LENGTH_SHORT).show();
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
            */}
        });

    }

}
