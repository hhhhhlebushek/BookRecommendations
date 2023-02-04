package com.example.bookrecommendations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/rubik_extra_bold.ttf"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Успех", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainListApp.class);
                startActivity(intent);
                /*FragmentManager manager = getSupportFragmentManager();
                Exit exit = new Exit();
                exit.show(manager, "myDialog");*/
            }
        });

    }
}