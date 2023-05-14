package com.example.bookrecommendations;

import static com.example.bookrecommendations.R.drawable.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CreateRec  extends AppCompatActivity {
    ListView listView;
    int h=0;
    ArrayList<Bitmap> img_bitmap=new ArrayList<Bitmap>();
    int[] myImageList1 = new int[]{roman1, roman2, roman3, roman4, roman5, roman6, roman7, roman8, roman9, roman10, roman11, roman12, roman13};
    int[] myImageList2 = new int[]{pov1, pov2, pov3, pov4, pov5};
    int[] myImageList3 = new int[]{ras1, ras2, ras3, ras4, ras5};
    int[] myImageList4 = new int[]{kom1, kom2, kom3, kom4, kom5};
    int[] myImageList5 = new int[]{fan1, fan2, fan3, fan4, fan5};

    final int[] imageArray =
            {image5,
                    icon,
                    image2,
                    book_start,
                    bookadviser, image_auto, textsplash, image3,image4,image5};
    //ArrayList<Object> arrayList=new ArrayList<>();
    //ArrayAdapter<Object> arrayAdapter; ArrayAdapter<Object> arrayAdapter1;
    private BufferedReader ImageIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        int f=1;
        setContentView(R.layout.acrivity_create_rec);
        //TextView t = findViewById(R.id.dop_p);
        listView=(ListView) findViewById(R.id.list);
        ArrayList<ElementList> arrayList=new ArrayList<>();
        ElementAdapter elementAdapter = new ElementAdapter(this, R.layout.item_rec, arrayList);
        //arrayAdapter=new ArrayAdapter<Object>(getApplicationContext(), R.layout.item_rec, arrayList);
        TextView c1 = new TextView(getApplicationContext());
        listView.setAdapter(elementAdapter);
        final int[] m = {0};

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
                        final String[] picture = new String[100];


                        List keys = null;
                        final String[] s = new String[100];
                        final String loc="l"; int count=0;
                        arrayList.clear();
                        //t.setText(result);
                        try {
                            //Log.i("tagconvertstr", "["+result+"]");

                            JSONObject object = new JSONObject(EncodingToUTF8(result));
                            title[0] = object.getString("stepen");
                            String value1, value2;

                            //count = object.getInt("count");
                            JSONArray jsonarray1 = object.optJSONArray("name");
                            JSONArray jsonarray2 = object.getJSONArray("author");
                            JSONArray jsonarray3 = object.getJSONArray("year");
                            JSONArray jsonarray4 = object.getJSONArray("stepen");

                            //t.setText(jsonarray1.toString());

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
                            String imageName[] = {"image1","image2","image2"};
                            for (int i = 0, j=1; i < listName.size(); i++, j++) {
                                String imageNam = "image"+j;
                                int st = getResources().getIdentifier(String.valueOf(imageName[0]), "drawable", getPackageName());
                                title[i] = listName.get(i).getString(getName(i + 1));
                                author[i] = listAuthor.get(i).getString(getName(i + 1));
                                year[i] = listYear.get(i).getString(getName(i + 1));
                                stepen[i] = listStepen.get(i).getString(getName(i + 1));
                                //picture[i] = String.valueOf(getResources().getStringArray(Integer.parseInt(imageName[i])));

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

c1.setBackgroundResource(icon);

                                c1.setCompoundDrawablesWithIntrinsicBounds(
                                        textsplash, 0, 0, 0);

c1.setWidth(10);
c1.setHeight(10);
                                int[] valid = { 0, 1, 2, 3, 4,5,6 };
                                try {
    if (title[i] == "null") continue;
    else{
        SpannableStringBuilder ssb = new SpannableStringBuilder(" Hello world!");
        ssb.setSpan(new ImageSpan(getApplicationContext(), icon), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        c1.setText(ssb, TextView.BufferType.SPANNABLE);

        int idx = new Random().nextInt(myImageList1.length);
        int randomInt = new Random().ints(1, 1, 11).findFirst().getAsInt();
        if(title[0].equals("Война и мир")) {
            h= Integer.parseInt(String.valueOf(getResources().getIdentifier(String.valueOf(myImageList1[m[0]]), "drawable", getPackageName())));
        }
        else if(title[1].equals("Драма на охоте"))
        {
            h= Integer.parseInt(String.valueOf(getResources().getIdentifier(String.valueOf(myImageList2[m[0]]), "drawable", getPackageName())));

        }
        else if(title[2].equals("Цветы для Элджернона"))
        {
            h= Integer.parseInt(String.valueOf(getResources().getIdentifier(String.valueOf(myImageList3[m[0]]), "drawable", getPackageName())));

        }
        else if(title[4].equals("Горе от ума"))
        {
            h= Integer.parseInt(String.valueOf(getResources().getIdentifier(String.valueOf(myImageList4[m[0]]), "drawable", getPackageName())));

        }
        else if(title[5].equals("Дюна"))
        {
            h= Integer.parseInt(String.valueOf(getResources().getIdentifier(String.valueOf(myImageList5[m[0]]), "drawable", getPackageName())));

        }

        //value2 = isbn[i] + "\n\n" + "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + "\n" + stepen[i];
        value2 = "Название: " + title[i] + "\n\n" + "Автор: " + author[i] + "\n\n" + "Год: " + year[i] + "\n\n" + "Степень рекомендации: " + "\n" + stepen[i];
        arrayList.add(new ElementList(h,value2));

        m[0] = m[0] + 1;
        //arrayList.add(value2);

        //img_bitmap.add(ImgBitFromFile("войнаимир"));
        /*File imgFile = new  File("C:/xampp/htdocs/picture/"+title[i]+".jpg");
        if(imgFile.exists())
        {
            ImageView myImage = new ImageView(getApplicationContext());
            myImage.setImageURI(Uri.fromFile(imgFile));
            arrayList.add(myImage);
        }*/

    }
}catch (Exception e1) {
                                    e1.printStackTrace();
                                    //t.setText(e1.toString());
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
                            elementAdapter.notifyDataSetChanged();
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
    public static Bitmap ImgBitFromFile(String file_name) {

        File imgFile = new File(
                "C:/xampp/htdocs/picture/" + file_name);
        //System.out.println("Image Exists:::" + imgFile.getAbsolutePath().toString());
        if (imgFile.exists()) {
            // System.gc();
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
                    .getAbsolutePath());
            //System.out.println("Image Exists:::");

            return myBitmap;
        }
        return null;
    }
}
