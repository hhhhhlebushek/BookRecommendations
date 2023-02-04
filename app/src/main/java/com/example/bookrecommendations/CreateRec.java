package com.example.bookrecommendations;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateRec  extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_create_rec);
        //TextView t = findViewById(R.id.t);
        listView=(ListView) findViewById(R.id.list);
        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.item_rec, arrayList);
        listView.setAdapter(arrayAdapter);


        Handler handler2 = new Handler();
        handler2.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                PutDataNew putData = new PutDataNew("http://192.168.56.1/tests/rec.php", "POST");
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        ArrayList<JSONObject> listName, listAuthor, listYear, listStepen,listISBN, listAdress, listTimeWork, listPhone, listQuantity,listPrice;
                        String result = putData.getResult();
                        final JSONObject[] a = new JSONObject[1];
                        final String[] isbn = new String[100];
                        final String[] title = new String[100];
                        final String[] author = new String[100];
                        final String[] year = new String[100];
                        final String[] stepen = new String[100];
                        /*final String[][] address = new String[100][100];
                        final String[][] timework = new String[100][100];
                        final String[][] numberphone = new String[100][100];
                        final String[][] quantity = new String[100][100];
                        final String[][] price = new String[100][100];*/

                        List keys = null;
                        final String[] s = new String[100];
                        final String loc="l"; int count=0;
                        arrayList.clear();
                        //t.setText(result);
                        try {
                            //Log.i("tagconvertstr", "["+result+"]");

                            JSONObject object = new JSONObject(EncodingToUTF8(result));
                            //title[0] = object.getString("name");
                            String value1, value2;
                            //count = object.getInt("count");
                            JSONArray jsonarray1 = object.getJSONArray("name");
                            JSONArray jsonarray2 = object.getJSONArray("author");
                            JSONArray jsonarray3 = object.getJSONArray("year");
                            JSONArray jsonarray4 = object.getJSONArray("stepen");
                            JSONArray jsonarray5 = object.getJSONArray("isbn");
                            //JSONArray jsonarray5 = object.toJSONArray(object.names());
                           /* s[0] = object.optString("address");
                            s[1] = object.optString("timeWork");
                            s[2] = object.optString("numberPhone");
                            s[3] = object.optString("quantity");
                            s[4] = object.optString("address");

                            JSONObject ad = object.optJSONObject("address");
                            JSONObject tw = object.optJSONObject("timeWork");
                            JSONObject np = object.optJSONObject("numberPhone");
                            JSONObject qu = object.optJSONObject("quantity");
                            JSONObject pr = object.optJSONObject("price");*/

                            /*JSONObject object1 = ad.optJSONObject("3");
                            String k = object1.keys().next();
                            count = ad.length();*/

                            //t.setText(s[0].toString());

                            //List<String> keysFromObj= (List<String>) object.keys();
                            //List<String> ii= findKeys(object,keysFromObj);

                            //JSONArray jsonarray1 = object.toJSONArray(object.names());

                            listName = getArrayListFromJSONArray(jsonarray1);
                            listAuthor = getArrayListFromJSONArray(jsonarray2);
                            listYear = getArrayListFromJSONArray(jsonarray3);
                            listStepen = getArrayListFromJSONArray(jsonarray4);
                            listISBN = getArrayListFromJSONArray(jsonarray5);
                            //listAdress = getArrayListFromJSONArray(s[0]);

                            //t.setText(tw.toString());
                            //JSONObject object11=null;
                           /* int y = 0;
                            String vv = null;
                            String kk = null;
                            final String[] KK1 = new String[20];
                            final String[] VV1 = new String[20];


                            final String[] VV2 = new String[20];
                            final String[] VV3 = new String[20];
                            final String[] VV4 = new String[20];
                            final String[] VV5 = new String[20];

                            JSONObject object11 = null;


                            int o=0;
                            for (int i = 0; i < 10; i++) {
                                if (ad.optJSONObject(String.valueOf(i)) != null) {
                                    object11 = ad.optJSONObject(String.valueOf(i));
                                    KK1[i] = object11.keys().next().toString();//ключ объекта
                                    VV1[i] = object11.getString(KK1[i]).toString();//нужное мне значение

                                    if(tw.names().optString(o) != null || tw.has(String.valueOf(o))) {
                                        VV2[o] = tw.optString(tw.names().optString(o).toString());//нужное мне значение
                                        VV3[o] = np.optString(np.names().optString(o).toString());//нужное мне значение
                                        VV4[o] = qu.optString(qu.names().optString(o).toString());//нужное мне значение
                                        VV5[o] = pr.optString(pr.names().optString(o).toString());//нужное мне значение

                                        System.out.println("VV2[" + o + "]: " + VV2[o]);
                                        System.out.println("VV3[" + o + "]: " + VV3[o]);
                                        System.out.println("VV4[" + o + "]: " + VV4[o]);
                                        System.out.println("VV5[" + o + "]: " + VV5[o]);
                                        System.out.flush();
                                        o++;
                                    }
                                    else continue;
                                } else
                                    continue;


                            }

                            //t.setText(VV2[2].toString());
                            o=0;*/
                            for (int i = 0; i < listName.size(); i++) {
                                isbn[i] = listISBN.get(i).getString(getName(i + 1));
                                //String q= String.valueOf(y);
                                //object11 = ad.optJSONObject("3");//индекс объекта
                                //JSONObject object11 = ad.optJSONObject(q.toString());//индекс объекта
                                String lol = "w";
                                for (int j = 0; j < 10; j++) {
                                    Log.i("isbn:", "[" + isbn[i] + "]");
                                    //Log.i("KK: ", "[" + KK1[j] + "]");
                                    Log.i("i:", "[" + i + "]");
                                    Log.i("j: ", "[" + j + "]");
                                    /*if (isbn[i].equals(KK1[j])) {
                                        //t.setText(j);
                                        lol = VV1[j];
                                        address[i][j] = VV1[j];
                                        timework[i][o] = VV2[o];
                                        numberphone[i][o] = VV3[o];
                                        quantity[i][o] = VV4[o];
                                        price[i][o] = VV5[o];
                                        //Log.i("address["+i+"]["+j+"]: ", "["+address[i][j]+"]");
                                        System.out.println("timework[" + i + "][" + j + "]: " + timework[i][o]);
                                        o++;
                                    }*/
                                }
                                //t.setText(address[2][4]);
                                //t.setText(address[i]);


                                //String object12 = ad.getString(String.valueOf(i));

                                title[i] = listName.get(i).getString(getName(i + 1));
                                author[i] = listAuthor.get(i).getString(getName(i + 1));
                                year[i] = listYear.get(i).getString(getName(i + 1));
                                stepen[i] = listStepen.get(i).getString(getName(i + 1));
                                //int j=0;
                                /*o=0;
                                for (int j = 0; j < 10; j++) {
                                    if (isbn[i].equals(KK1[j])) {
                                        value1 = i + " " + j + "ISBN: " + isbn[i] + "\n\n" + "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + stepen[i] + "\n\n" + "адрес: " + address[i][j] + "\n\n" + "рабочее время: " + timework[i][o] + "\n\n" + "номер телефона: " + numberphone[i][o] + "\n\n" + "количество экземпляров: " + quantity[i][o]+ "\n\n" + "усредненная стоимость: " + price[i][o];
                                        o++;
                                    } else {
                                        continue;
                                    }
                                    arrayList.add(value1);
                                }*/
                                if (title[i] != "null" && author[i] != "null" && year[i] != "null" && stepen[i] != "null") {
                                    //value2 = isbn[i] + "\n\n" + "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + "\n" + stepen[i];
                                    value2 = "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + "\n" + stepen[i];
                                    arrayList.add(value2);
                                }
                                /*if(title[i]!= "null" && author[i]!= "null" && year[i]!= "null" && stepen[i]!= "null") {
                                    do{
                                        if (isbn[i].equals(address[i][j])) {
                                            value1 = i + " " + j + "ISBN: " + isbn[i] + "\n\n" + "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + "\n" + stepen[i]+ "\n\n" + "адрес: " + "\n" + address[i][j];
                                        }
                                        else{
                                            value1 = i + " " + j + "ISBN: " + isbn[i] + "\n\n" + "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + "\n" + stepen[i];
                                        }
                                        arrayList.add(value1);
                                    }while (j<10);
                                }*/


                            }
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
    public  static  List<String> findKeys(JSONObject obj, List keys) throws JSONException {
        List<String>keysFromObj= (List<String>) obj.keys();
        keys.addAll(keysFromObj);
        for(String key:keysFromObj){
            if(obj.get(key).getClass()==JSONObject.class){
                findKeys((JSONObject) obj.get(key),keys);
            }
        }
        return keysFromObj;
    }

}
