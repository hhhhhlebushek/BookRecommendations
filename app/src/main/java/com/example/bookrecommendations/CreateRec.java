package com.example.bookrecommendations;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class CreateRec  extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_create_rec);
        TextView t = findViewById(R.id.t);
        listView=(ListView) findViewById(R.id.list);
        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.item_rec, arrayList);
        listView.setAdapter(arrayAdapter);


        Handler handler2 = new Handler();
        handler2.post(new Runnable() {
            @Override
            public void run() {
                PutDataNew putData = new PutDataNew("http://192.168.56.1/tests/rec.php", "POST");
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        ArrayList<JSONObject> listName, listAuthor, listYear, listStepen;
                        String result = putData.getResult();
                        final JSONObject[] a = new JSONObject[1];
                        final String[] title = new String[100];
                        final String[] author = new String[100];
                        final String[] year = new String[100];
                        final String[] stepen = new String[100];
                        final String loc="l"; final int count=0;
                        arrayList.clear();
                        //t.setText(result);
                        try {
                            //Log.i("tagconvertstr", "["+result+"]");

                            JSONObject object = new JSONObject(EncodingToUTF8(result));
                            //title[0] = object.getString("name");
                            String value;
                            //count = object.getInt("count");
                            JSONArray jsonarray1 = object.getJSONArray("name");
                            JSONArray jsonarray2 = object.getJSONArray("author");
                            JSONArray jsonarray3 = object.getJSONArray("year");
                            JSONArray jsonarray4 = object.getJSONArray("stepen");
                            //JSONArray jsonarray1 = object.toJSONArray(object.names());

                            listName = getArrayListFromJSONArray(jsonarray1);
                            listAuthor = getArrayListFromJSONArray(jsonarray2);
                            listYear = getArrayListFromJSONArray(jsonarray3);
                            listStepen= getArrayListFromJSONArray(jsonarray4);
                            //t.setText(listItems.get(0).getString("1"));
                            //t.setText(count);
                            for(int i=0;i<listName.size();i++) {
                                title[i] = listName.get(i).getString(getName(i+1));
                                author[i] = listAuthor.get(i).getString(getName(i+1));
                                year[i] = listYear.get(i).getString(getName(i+1));
                                stepen[i] = listStepen.get(i).getString(getName(i+1));
                                if(title[i]!= "null" && author[i]!= "null" && year[i]!= "null" && stepen[i]!= "null") {
                                    value = "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + "\n" + stepen[i];
                                    arrayList.add(value);
                                }
                            }
                            arrayAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            t.setText(e.toString());
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

}
