package com.example.bookrecommendations;

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

import org.json.JSONException;

import javax.xml.transform.sax.SAXResult;

public class NoteNew extends AppCompatActivity {
    EditText editTextTitle, editTextText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        Button add = findViewById(R.id.add);
        editTextTitle = findViewById(R.id.title);
        editTextText = findViewById(R.id.text);
        add.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
        //final String title = editTextTitle.getText().toString();



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String title = String.valueOf(editTextTitle.getText());
                    if (!title.equals("")) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String[] field = new String[3];
                                field[0] = "id";
                                field[1] = "title";
                                field[2] = "text";

                                String[] data = new String[3];
                                data[0] = id;
                                data[1] = String.valueOf(editTextTitle.getText());
                                data[2] = String.valueOf(editTextText.getText());

                                PutData putData = new PutData("http://192.168.56.1/login/AddNotes.php", "POST", field, data);

                                if (putData.startPut()) {
                                    if (putData.onComplete()) {

                                        String result = putData.getResult();

                                        if (result.equals("Success 1")) {
                                            Toast.makeText(getApplicationContext(), "Заметка добавлена! :)", Toast.LENGTH_SHORT).show();
                                            Intent intent =null;
                                            intent = new Intent(getApplicationContext(), MainListApp.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        });
                    }
                    else
                    Toast.makeText(getApplicationContext(), "Введите имя заметки", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
                e.printStackTrace();
                    System.out.println(e.toString());
            }
        }
        });
    }

}
