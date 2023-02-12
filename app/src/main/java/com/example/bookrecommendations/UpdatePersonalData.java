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
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class UpdatePersonalData extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    EditText NewUsername,NewUserFam,NewUserEmail,NewUserPhone;

    //TextView textViewSignUp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_data);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        String fam = sharedPreferences.getString("fam", "");
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String number = sharedPreferences.getString("number", "");

        TextView f = findViewById(R.id.fam);
        TextView na = findViewById(R.id.name);
        TextView e = findViewById(R.id.email);
        TextView n = findViewById(R.id.number);
        TextView p = findViewById(R.id.updatepersonaldata);
        p.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/rubik_semi_bold.ttf"));

        f.setText(fam.toString());
        na.setText(name.toString());
        e.setText(email.toString());
        n.setText(number.toString());

        NewUsername = findViewById(R.id.newName);
        NewUserFam = findViewById(R.id.newFam);
        NewUserEmail = findViewById(R.id.newEmail);
        NewUserPhone = findViewById(R.id.newPhone);
        Button UpdateName,UpdateFam,UpdateEmail,UpdatePhone;
        UpdateName = findViewById(R.id.buttonNewName);
        UpdateFam = findViewById(R.id.buttonNewFam);
        UpdateEmail = findViewById(R.id.buttonNewEmail);
        UpdatePhone = findViewById(R.id.buttonNewPhone);
        UpdateName.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));
        UpdateFam.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));
        UpdateEmail.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));
        UpdatePhone.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));

        UpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name;

                name = String.valueOf(NewUsername.getText());

                if (!name.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "firstName";
                            field[1] = "id";

                            String[] data = new String[2];
                            data[0] = name;
                            data[1] = id;
                            PutData putData = new PutData("http://192.168.56.1/login/updateData.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Имя удачно обновлено!", Toast.LENGTH_SHORT).show();
                                        save("1", "name", name);
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    NewUsername.setText(null);
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Не все обязательные поля заполнены", Toast.LENGTH_SHORT).show();
                }
            }
        });

        UpdateFam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String lastname;

                lastname = String.valueOf(NewUserFam.getText());

                if (!lastname.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "lastname";
                            field[1] = "id";

                            String[] data = new String[2];
                            data[0] = lastname;
                            data[1] = id;
                            PutData putData = new PutData("http://192.168.56.1/login/updateData.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Фамилия удачно обновлена!", Toast.LENGTH_SHORT).show();
                                        save("1", "fam", lastname);
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    NewUserFam.setText(null);
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Не все обязательные поля заполнены", Toast.LENGTH_SHORT).show();
                }
            }
        });

        UpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email;

                email = String.valueOf(NewUserEmail.getText());

                if (!email.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "id";

                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = id;
                            PutData putData = new PutData("http://192.168.56.1/login/updateData.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Email удачно обновлен!", Toast.LENGTH_SHORT).show();
                                        save("1", "email", email);
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    NewUserEmail.setText(null);
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Не все обязательные поля заполнены", Toast.LENGTH_SHORT).show();
                }
            }
        });
        UpdatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone;

                phone = String.valueOf(NewUserPhone.getText());

                if (!phone.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "numberphone";
                            field[1] = "id";

                            String[] data = new String[2];
                            data[0] = phone;
                            data[1] = id;
                            PutData putData = new PutData("http://192.168.56.1/login/updateData.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Номер телефона удачно обновлен!", Toast.LENGTH_SHORT).show();
                                        save("1", "number", phone);
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                    NewUserPhone.setText(null);
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
    public void save(String str, String type, String s) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("check", str);
        if (type.equals("fam"))
        myEdit.putString("fam", s);
        else if (type.equals("name"))
        myEdit.putString("name", s);
        else if (type.equals("email"))
        myEdit.putString("email", s);
        else if (type.equals("number"))
        myEdit.putString("number", s);
        myEdit.commit();
    }

}
