package com.example.bookrecommendations;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;


public class AddTest extends AppCompatActivity {
    public static ArrayList<String> arrayList = new ArrayList<String>();
    TextView number;
    EditText editTextNameTest, editTextNameQuastions, editTextVar1, editTextVar2, editTextVar3, editTextVar4, editTextVar5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        editTextNameTest = findViewById(R.id.textNameTest);
        editTextNameQuastions = findViewById(R.id.textQuestion);
        number = findViewById(R.id.number);
        editTextVar1 = findViewById(R.id.var1);
        editTextVar2 = findViewById(R.id.var2);
        editTextVar3 = findViewById(R.id.var3);
        editTextVar4 = findViewById(R.id.var4);
        editTextVar5 = findViewById(R.id.var5);
        Button next= findViewById(R.id.buttonNext);

        final String[] NameQuastions = new String[10];
        final String[] answers1 = new String[5];

        final int[] i = {1}; NameQuastions[0]="null";
        next.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
        arrayList.clear();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i[0] >=1 && i[0] < 11) {
                    i[0] = mas(NameQuastions, answers1, i[0]);
                    number.setText("Вопрос " + i[0] + " из 10");
                    arrayList.add(Arrays.toString(answers1));
                    if (i[0] == 10) {
                        next.setText("Завершить");
                    } else if (i[0] == 11) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Handler handler1 = new Handler();
                        handler1.post(new Runnable() {
                            @Override
                            public void run() {
                                String[] field = new String[3];
                                field[0] = "nameTest";
                                field[1] = "nameQuastions";
                                field[2] = "answers";

                                String[] data1 = new String[3];

                                data1[0] = editTextNameTest.getText().toString();
                                data1[1] = Arrays.toString(NameQuastions);
                                data1[2] = arrayList.toString();
                                //Toast.makeText(getApplicationContext(), data1[0].toString(), Toast.LENGTH_SHORT).show();
                                PutData putData = new PutData("http://192.168.56.1/tests/add_test.php", "POST", field, data1);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        Intent intent;
                                        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        if (result.equals("Success")) {
                                            Toast.makeText(getApplicationContext(), "Ваши ответы получены", Toast.LENGTH_SHORT).show();
                                        } else if (result.equals("Wrong") || result.equals("Error")) {
                                            Toast.makeText(getApplicationContext(), "Вы не выбрали ни одного варианта!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                            }
                        });
                    }
                }
            }
        });
    }
    public int mas(String[]nameQuastios,String[]answers, int i){
        int j =i-1;
        nameQuastios[j]=String.valueOf(editTextNameQuastions.getText());
        answers[0]=String.valueOf(editTextVar1.getText());
        answers[1]=String.valueOf(editTextVar2.getText());
        answers[2]=String.valueOf(editTextVar3.getText());
        answers[3]=String.valueOf(editTextVar4.getText());
        answers[4]=String.valueOf(editTextVar5.getText());
        System.out.println("nameQuastios["+i+"]: " + nameQuastios[j]);
        System.out.println("1: " + answers[0]);
        System.out.println("2: " + answers[1]);
        System.out.println("3: " + answers[2]);
        System.out.println("4: " + answers[3]);
        System.out.println("5: " + answers[4]);
        editTextNameQuastions.setText(null);
        editTextVar1.setText(null);
        editTextVar2.setText(null);
        editTextVar3.setText(null);
        editTextVar4.setText(null);
        editTextVar5.setText(null);
        i++;
        return i;
    }
}

