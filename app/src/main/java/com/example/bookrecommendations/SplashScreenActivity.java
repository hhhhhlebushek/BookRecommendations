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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String str = sharedPreferences.getString("check", "");
                Intent i;
                if (str.equals("1")) {
                    i = new Intent(SplashScreenActivity.this, MainActivity.class);
                } else {
                    i = new Intent(SplashScreenActivity.this, LogIn.class);
                }
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}