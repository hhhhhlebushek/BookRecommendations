package com.example.bookrecommendations;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.ContentValues;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainListApp extends AppCompatActivity {


    BottomNavigationView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_app);

        btn = findViewById(R.id.nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_c, new TestsFragment()).commit();
        btn.setSelectedItemId(R.id.home);
        btn.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new TestsFragment();
                        break;
                    case R.id.directory:
                        fragment = new RecommendationsFragment();
                        break;
                    case R.id.basket:
                        fragment = new NotesFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_c, fragment).commit();
                return true;
            }
        });


    }
}

