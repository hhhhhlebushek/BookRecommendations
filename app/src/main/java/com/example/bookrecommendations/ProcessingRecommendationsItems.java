package com.example.bookrecommendations;

import static android.view.KeyEvent.KEYCODE_BACK;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProcessingRecommendationsItems  extends AppCompatActivity {
    ListView listView;
    SearchView searchView;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing_recommendations_items);
        Button save = findViewById(R.id.save);
        save.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/rubik_extra_bold.ttf"));
        Button delete = findViewById(R.id.delete);
        delete.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/rubik_extra_bold.ttf"));
        Bundle arguments = getIntent().getExtras();
        String isbn = arguments.get("isbn").toString();
        String name = arguments.get("name").toString();
        String author = arguments.get("author").toString();
        String year = arguments.get("year").toString();
        String keys = arguments.get("keys").toString();

        TextView n = findViewById(R.id.name);
        TextView a = findViewById(R.id.author);
        TextView y = findViewById(R.id.year);
        TextView k = findViewById(R.id.keys);

        n.setText(name.toString());
        a.setText(author.toString());
        y.setText(year.toString());
        k.setText(keys.toString());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String[] field = new String[2];
                        field[0] = "result";
                        field[1] = "isbn";

                        String[] data = new String[2];
                        data[0] = "save";
                        data[1] = isbn;

                        PutData putData = new PutData("http://192.168.56.1/tests/recommendationRrocessingSaveOrDelete.php", "POST", field, data);

                        if (putData.startPut()) {
                            if (putData.onComplete()) {

                                String result = putData.getResult();

                                if (result.equals("Success 1")) {
                                    Toast.makeText(getApplicationContext(), "Рекомендация сохранена в общую базу и удалена из временного хранилища! :)", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainListApp.class);
                                    startActivity(intent);
                                    Intent intent1 = new Intent(getApplicationContext(), ProcessingRecommendations.class);
                                    startActivity(intent1);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler1 = new Handler(Looper.getMainLooper());
                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                        String[] field = new String[2];
                        field[0] = "result";
                        field[1] = "isbn";

                        String[] data = new String[2];
                        data[0] = "delete";
                        data[1] = isbn;

                        PutData putData = new PutData("http://192.168.56.1/tests/recommendationRrocessingSaveOrDelete.php", "POST", field, data);

                        if (putData.startPut()) {
                            if (putData.onComplete()) {

                                String result = putData.getResult();

                                if (result.equals("Success 2")) {
                                    Toast.makeText(getApplicationContext(), "Рекомендация удалена из временного хранилища! :)", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainListApp.class);
                                    startActivity(intent);
                                    Intent intent1 = new Intent(getApplicationContext(), ProcessingRecommendations.class);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
        });
    }

}