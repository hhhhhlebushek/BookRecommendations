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

        String NameTest = new String();
        final String[] NameQuastions = new String[100];
        final String[] answers1 = new String[5];
        final String[] answers2 = new String[5];
        final String[] answers3 = new String[5];
        final String[] answers4 = new String[5];
        final String[] answers5 = new String[5];
        final String[] answers6 = new String[5];
        final String[] answers7 = new String[5];
        final String[] answers8 = new String[5];
        final String[] answers9 = new String[5];
        final String[] answers10 = new String[5];
        NameTest = String.valueOf(editTextNameQuastions.getText());
        final int[] i = {1}; final int[] j = {0};
        number.setText("Вопрос "+i[0]+" из 10");
        //number.setText("Вопрос "+i[0]+" из 10");
        next.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
        String finalNameTest = NameTest;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int j=i[0]-1;
                if(i[0]<3) {
                    NameQuastions[j] = String.valueOf(editTextNameQuastions.getText());
                    answers1[0] = String.valueOf(editTextVar1.getText());
                    answers1[1] = String.valueOf(editTextVar2.getText());
                    answers1[2] = String.valueOf(editTextVar3.getText());
                    answers1[3] = String.valueOf(editTextVar4.getText());
                    answers1[4] = String.valueOf(editTextVar5.getText());
                    System.out.println("nameQuastios[" + j + "]: " + NameQuastions[j]);
                    System.out.println("1: " + answers1[0]);
                    System.out.println("2: " + answers1[1]);
                    System.out.println("3: " + answers1[2]);
                    System.out.println("4: " + answers1[3]);
                    System.out.println("5: " + answers1[4]);
                    i[0] = i[0] + 1;
                    number.setText("Вопрос " + i[0] + " из 10");
                }
                else if(i[0]==1){
                    next.setText("Завершить");
                }
                else{
                    Handler handler1 = new Handler();
                    handler1.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "nameTest";
                            field[1] = "nameQuastions";
                            field[2] = "q1";
                            field[3] = "q2";
                            /*field[4] = "q3";
                            field[5] = "q4";
                            field[6] = "q5";
                            field[7] = "q6";
                            field[8] = "q7";
                            field[9] = "q8";
                            field[10] = "q9";
                            field[11] = "q10";*/

                            String[] data1 = new String[4];

                            data1[0] = finalNameTest;
                            data1[1] = NameQuastions.toString();
                            data1[2] = answers1.toString();
                            data1[3] = answers2.toString();
                            /*data1[4] = answers3.toString();
                            data1[5] = answers4.toString();
                            data1[6] = answers5.toString();
                            data1[7] = answers6.toString();
                            data1[8] = answers7.toString();
                            data1[9] = answers8.toString();
                            data1[10] = answers9.toString();
                            data1[11] = answers10.toString();*/


                            PutData putData = new PutData("http://192.168.56.1/tests/add_test.php", "POST", field, data1);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    Intent intent;
                                    //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    if (result.equals("Success")) {
                                        Toast.makeText(getApplicationContext(), "Ваши ответы получены", Toast.LENGTH_SHORT).show();
                                    }
                                    else if(result.equals("Wrong") || result.equals("Error")){
                                        Toast.makeText(getApplicationContext(), "Вы не выбрали ни одного варианта!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        }
                    });
                }
            }
        });
    }
    public int mas(String[]nameQuastios,String[]answers, int i){
        //nameTest[i]=String.valueOf(editTextNameTest.getText());
        nameQuastios[i]=String.valueOf(editTextNameQuastions.getText());
        answers[0]=String.valueOf(editTextVar1.getText());
        answers[1]=String.valueOf(editTextVar2.getText());
        answers[2]=String.valueOf(editTextVar3.getText());
        answers[3]=String.valueOf(editTextVar4.getText());
        answers[4]=String.valueOf(editTextVar5.getText());
        System.out.println("nameQuastios["+i+"]: " + nameQuastios[i]);
        System.out.println("1: " + answers[0]);
        System.out.println("2: " + answers[1]);
        System.out.println("3: " + answers[2]);
        System.out.println("4: " + answers[3]);
        System.out.println("5: " + answers[4]);

        /*editTextNameQuastions.setText(null);
        editTextVar1.setText(null);
        editTextVar2.setText(null);
        editTextVar3.setText(null);
        editTextVar4.setText(null);
        editTextVar5.setText(null);*/
        i++;
        System.out.println("aaaa: " + i);
        return i;
    }
}

