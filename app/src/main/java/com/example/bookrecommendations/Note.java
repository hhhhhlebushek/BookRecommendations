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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Note extends AppCompatActivity {
    EditText editTextTitle, editTextText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");

        Bundle arguments = getIntent().getExtras();
        String title = arguments.get("name_note").toString();
        String text = arguments.get("text_note").toString();
        String id_note = arguments.get("id_note").toString();
        Button save = findViewById(R.id.save);
        Button delete = findViewById(R.id.delete);
        save.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
        delete.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/rubik_extra_bold.ttf"));

        editTextTitle = findViewById(R.id.login);
        editTextText = findViewById(R.id.password);
        editTextTitle.setText(title); editTextText.setText(text);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = String.valueOf(editTextTitle.getText());

                if (!newTitle.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[5];
                            field[0] = "result";
                            field[1] = "id";
                            field[2] = "id_note";
                            field[3] = "title";
                            field[4] = "text";

                            String[] data = new String[5];
                            data[0] = "save";
                            data[1] = id;
                            data[2] = id_note;
                            data[3] = String.valueOf(editTextTitle.getText());
                            data[4] = String.valueOf(editTextText.getText());

                            PutData putData = new PutData("http://192.168.56.1/login/SaveNotes.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success 1")) {
                                        Toast.makeText(getApplicationContext(), "Изменения сохранены! :)", Toast.LENGTH_SHORT).show();
                                        Intent intent =null;
                                        intent = new Intent(getApplicationContext(), MainListApp.class);


                                        startActivity(intent);
                                        /*Handler handler1 = new Handler(Looper.getMainLooper());
                                        handler1.post(new Runnable() {
                                            @Override
                                            public void run() {

                                                String[] field = new String[2];
                                                field[0] = "id";
                                                field[1] = "id_note";

                                                String[] data = new String[2];
                                                data[0] = id;
                                                data[1] = id_note;
                                                PutData putData = new PutData("http://192.168.56.1/login/DataNotes.php", "POST", field, data);
                                                if (putData.startPut()) {
                                                    if (putData.onComplete()) {
                                                        ArrayList<JSONObject> listItems;
                                                        String result = putData.getResult();
                                                        final String[] title = new String[1];
                                                        final String[] text = new String[1];
                                                        try {
                                                            JSONObject object = new JSONObject(EncodingToUTF8(result));

                                                            JSONArray jsonarray = object.getJSONArray("data");
                                                            listItems = getArrayListFromJSONArray(jsonarray);

                                                            title[0] = listItems.get(0).getString("name_note");
                                                            text[0] = listItems.get(0).getString("text_note");

                                                            editTextTitle.setText(title[0]); editTextText.setText(text[0]);


                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }

                                                    }
                                                }
                                            }
                                        });*/
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
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String[] field = new String[3];
                        field[0] = "result";
                        field[1] = "id";
                        field[2] = "id_note";

                        String[] data = new String[3];
                        data[0] = "delete";
                        data[1] = id;
                        data[2] = id_note;

                        PutData putData = new PutData("http://192.168.56.1/login/DeleteNotes.php", "POST", field, data);

                        if (putData.startPut()) {
                            if (putData.onComplete()) {

                                String result = putData.getResult();

                                if (result.equals("Success 2")) {
                                    Toast.makeText(getApplicationContext(), "Заметка удалена! :)", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainListApp.class);
                                    startActivity(intent);
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
    public  static  String EncodingToUTF8(String response){
        try {
            byte[] code = response.toString().getBytes("ISO-8859-1");
            response = new String(code, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public  static  String getName(int i){
        return String.valueOf(i);
    }

    private ArrayList< JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){
        ArrayList< JSONObject> aList = new ArrayList< JSONObject>();
        try {
            if(jsonArray!= null){
                for(int i = 0; i< jsonArray.length();i++){
                    aList.add(jsonArray.getJSONObject(i));
                }
            }
        }catch (JSONException js){
            js.printStackTrace();
        }
        return aList;
    }
}
