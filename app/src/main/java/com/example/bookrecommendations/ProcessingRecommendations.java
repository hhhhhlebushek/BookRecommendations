package com.example.bookrecommendations;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ProcessingRecommendations  extends AppCompatActivity {
    ListView listView;
    SearchView searchView;

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing_recommendations);
        listView=(ListView) findViewById(R.id.list);
        //TextView t=findViewById(R.id.textNameTest);
        arrayAdapter=new ArrayAdapter<String>(this, R.layout.item, arrayList);
        listView.setAdapter(arrayAdapter);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                PutDataNew putData = new PutDataNew("http://192.168.56.1/tests/processingRecommendations.php", "POST");
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        ArrayList<JSONObject> listItems0, listItems, listItems1, listItems2, listItems3;
                        String result = putData.getResult();

                        final String[] isbn = new String[100];
                        final String[] name = new String[100];
                        final String[] author = new String[100];
                        final String[] year = new String[100];
                        final String[] keys = new String[100];
                        final String[][] k = new String[100][100];
                        final String loc="l";

                        try {
                            JSONObject object = new JSONObject(EncodingToUTF8(result));
                            JSONArray object1 = new JSONArray();
                            String s;

                            JSONArray jsonarray0 = object.getJSONArray("isbn");
                            listItems0 = getArrayListFromJSONArray(jsonarray0);

                            JSONArray jsonarray = object.getJSONArray("name");
                            listItems = getArrayListFromJSONArray(jsonarray);

                            JSONArray jsonarray1 = object.getJSONArray("author");
                            listItems1 = getArrayListFromJSONArray(jsonarray1);

                            JSONArray jsonarray2 = object.getJSONArray("year");
                            listItems2 = getArrayListFromJSONArray(jsonarray2);

                            JSONArray jsonarray3 = object.getJSONArray("keys");
                            //listItems3 = getArrayListFromJSONArray(jsonarray3);


                            for(int i=0;i<jsonarray3.length();i++) {
                                keys[i] = jsonarray3.get(i).toString().replaceAll("\\[", "").replaceAll("\\]","");

                            }

                            //t.setText(titles[2]);
                            for(int i=0;i<listItems.size();i++) {
                                isbn[i] = listItems0.get(i).getString(getName(i+1));
                                name[i] = listItems.get(i).getString(getName(i+1));
                                author[i] = listItems1.get(i).getString(getName(i+1));
                                year[i] = listItems2.get(i).getString(getName(i+1));
                                arrayList.add((i+1)+". "+name[i]);

                                listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> arg0, View view, int i, long arg3) {
                                        Intent intent =null;
                                        intent = new Intent(getApplicationContext(), ProcessingRecommendationsItems.class);
                                        intent.putExtra("isbn", isbn[i]);
                                        intent.putExtra("name", name[i]);
                                        intent.putExtra("author", author[i]);
                                        intent.putExtra("year", year[i]);
                                        intent.putExtra("keys", keys[i]);
                                        startActivity(intent);
                                    }
                                });

                            }
                            //t.setText(jsonarray3.get(0).toString());
                            //t.setText(Arrays.toString(keys));
                            arrayAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //t.setText(e.toString());
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