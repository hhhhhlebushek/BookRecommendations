package com.example.bookrecommendations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.StartTheme);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String str = sharedPreferences.getString("check", "");
                //Intent i;
                if (str.equals("1")) {
                    Intent intent = new Intent(getApplicationContext(), MainListApp.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(intent);
                    finish();
                }

    }
}