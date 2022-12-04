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

public class QuizeActivity extends AppCompatActivity {
    public static ArrayList<String> arrayList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_test);
        TextView nameTest = findViewById(R.id.nameTest);
        TextView number = findViewById(R.id.number);
        TextView question = findViewById(R.id.question);
        TextView t = findViewById(R.id.t);
        Button next= findViewById(R.id.buttonNext);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Bundle arguments = getIntent().getExtras();
                String data = arguments.get("nameTest").toString();
                final int[] index = {1};
                String[] field = new String[1];
                field[0] = "nameTest";

                String[] DATA = new String[1];
                DATA[0] = data;
                PutData putData = new PutData("http://192.168.56.1/tests/p.php", "POST", field, DATA);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        ArrayList<JSONObject> listItems, listItems1, listItems2, listItems3, listItems4;
                        String result = putData.getResult();
                        if (result.equals("wrong") || result.equals("Error") ) {
                            Toast.makeText(getApplicationContext(), "бебебе", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            //Toast.makeText(getApplicationContext(), "Вы не выбрали ни одного варианта!", Toast.LENGTH_SHORT).show();
                            int N = 10;
                            int a=1;
                            int size =0;
                            final String[] title = new String[100];
                            final String[][] ask = new String[100][100];
                            final String loc="l";
                            try {
                                JSONObject object = new JSONObject(EncodingToUTF8(result));
                            /*Bundle argument = getIntent().getExtras();
                            String titles = argument.get("quastionsTest").toString();
                            String ask1 = argument.get("var1").toString();
                            String ask2 = argument.get("var2").toString();
                            String ask3 = argument.get("var3").toString();
                            String ask4 = argument.get("var4").toString();*/
                                size =object.length();
                                size--;
                                System.out.println("size: " + size);
                                //JSONArray jsonarray = object.toJSONArray(object.names());
                                JSONArray jsonarray = object.getJSONArray("titles");
                                listItems = getArrayListFromJSONArray(jsonarray);

                                JSONArray jsonarray1 = object.getJSONArray("ask1");
                                listItems1 = getArrayListFromJSONArray(jsonarray1);

                                JSONArray jsonarray2 = object.getJSONArray("ask2");
                                listItems2 = getArrayListFromJSONArray(jsonarray2);

                                JSONArray jsonarray3 = object.getJSONArray("ask3");
                                listItems3 = getArrayListFromJSONArray(jsonarray3);

                                JSONArray jsonarray4 = object.getJSONArray("ask4");
                                listItems4 = getArrayListFromJSONArray(jsonarray4);


                                for(int i=0;i<listItems.size();i++) {
                                    title[i] = listItems.get(i).getString(getName(i+1));
                                    //t.setText(title[i].toString());
                                }
                                for (int i = 0; i < listItems1.size(); i++) {
                                    ask[0][i] = listItems1.get(i).getString(getName(i + 1));
                                    ask[1][i] = listItems2.get(i).getString(getName(i + 1));
                                    ask[2][i] = listItems3.get(i).getString(getName(i + 1));
                                    ask[3][i] = listItems4.get(i).getString(getName(i + 1));
                                }
                                arrayList.clear();

                                data=data.replace("_", " ");
                                nameTest.setText(data);
                                number.setText("Вопрос 1 из 10");
                                question.setText(title[0]);
                                asks(listItems1,ask, 0);
                                index[0]=1;
                                int finalSize = size;
                                String finalData = data;
                                next.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
                                next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        int j= 1;
                                        if (index[0] >=1 && index[0] < finalSize){
                                            nameTest.setText(finalData);
                                            question.setText(title[index[0]]);
                                            index[0] = asks(listItems1,ask, index[0]);
                                            number.setText("Вопрос "+index[0]+" из 10");
                                            if(index[0] == finalSize){
                                                next.setText("Получить рекомендации");
                                            }
                                        }
                                        else{
                                            String[] mas = arrayList.toArray(new String[arrayList.size()]);
                                            Handler handler1 = new Handler();
                                            handler1.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    String[] field = new String[1];
                                                    field[0] = "result";
                                                    int size= arrayList.size();
                                                    String[] data = new String[size];
                                                /*for(int i=0; i<size; i++){
                                                    data[i] = arrayList.get(i);
                                                }*/
                                                    data[0]=arrayList.toString();
                                                    PutData putData = new PutData("http://192.168.56.1/tests/resultMainTest.php", "POST", field, data);
                                                    if (putData.startPut()) {
                                                        if (putData.onComplete()) {
                                                            String result = putData.getResult();
                                                            Intent intent;
                                                            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                            if (result.equals("Success")) {
                                                                //Toast.makeText(getApplicationContext(), "Ваши ответы получены", Toast.LENGTH_SHORT).show();
                                                                intent = new Intent(getApplicationContext(), CreateRec.class);
                                                                startActivity(intent);
                                                            }
                                                            else if(result.equals("Wrong")){
                                                                Toast.makeText(getApplicationContext(), "Вы не выбрали ни одного варианта!", Toast.LENGTH_SHORT).show();
                                                                intent = new Intent(getApplicationContext(),TestsFragment.class);
                                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);
                                                                overridePendingTransition (0, 0);
                                                            }
                                                            else if(result.equals("Error")){
                                                                Toast.makeText(getApplicationContext(), "Ошибка!", Toast.LENGTH_SHORT).show();
                                                                intent = new Intent(getApplicationContext(),TestsFragment.class);
                                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);
                                                                overridePendingTransition (0, 0);
                                                            }
                                                            else {
                                                                Toast.makeText(getApplicationContext(), "Ошибка!", Toast.LENGTH_SHORT).show();
                                                                intent = new Intent(getApplicationContext(),TestsFragment.class);
                                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);
                                                                overridePendingTransition (0, 0);
                                                            }
                                                            //startActivity(intent);

                                                            //Toast.makeText(getApplicationContext(), data[0], Toast.LENGTH_SHORT).show();
                                                        }
                                                    }


                                                    //Toast.makeText(getApplicationContext(), data[0], Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                            //Toast.makeText(getApplicationContext(), arrayList.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }// end onClick
                                });
                                //t.setText(Arrays.deepToString(ask));
                                //t.setText(Arrays.toString(ask));

                            } catch (JSONException e) {
                                e.printStackTrace();
                                t.setText(e.toString());
                            }
                        }
                    }
                }
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

    public  static  String getName(int i){
        return String.valueOf(i);
    }
    public int asks(ArrayList<JSONObject> listItems1,String[][] ask, int j){
        LinearLayout constraintLayout;
        constraintLayout = (LinearLayout)findViewById(R.id.linearLayout);
        constraintLayout.removeAllViews();
        for (int i = 0; i < listItems1.size(); i++) {

            CheckBox c = new CheckBox(getApplicationContext());
            Button l = new Button(getApplicationContext());
            l.setStateListAnimator(null);
            c.setText(ask[j][i]);
            String finalR = ask[j][i];
            c.setBackgroundResource(R.drawable.btn_item_in_profile);
            c.setWidth(12);
            c.setButtonDrawable(R.drawable.nocheck);
            c.setHeight(115);
            l.setHeight(1);
            c.setTextColor(Color.parseColor("#F38269"));
            c.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
            l.setBackgroundResource(R.drawable.btn_null);
            c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        c.setButtonDrawable(R.drawable.check);
                        c.setHeight(115);
                        //Toast.makeText(getApplicationContext(), finalR, Toast.LENGTH_SHORT).show();
                        QuizeActivity.arrayList.add(finalR);
                    }
                    else{
                        QuizeActivity.arrayList.remove(finalR);
                        c.setButtonDrawable(R.drawable.nocheck);
                        c.setHeight(115);
                    }
                }
            });
            constraintLayout.addView(c);constraintLayout.addView(l);
        }
        j++;
        return j;
    }
}
