package com.example.bookrecommendations;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LogIn extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    ListView listView;
    //TextView textViewSignUp;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       /* editTextUsername = findViewById(R.id.loginUsername);
        editTextPassword = findViewById(R.id.loginPassword);
        progressBar = findViewById(R.id.loginProgress);*/
    }
    public void onClickReg(View v){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
    public void onClick(View v) {
        loginButton();
    }

    public void loginButton(){
        Button input;
        TextView t=findViewById(R.id.t);
        input = findViewById(R.id.input);
        input.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/rubik_extra_bold.ttf"));
        editTextUsername = findViewById(R.id.login);
        editTextPassword = findViewById(R.id.password);
        final String login, pass, tt;
        login = String.valueOf(editTextUsername.getText());
        pass = String.valueOf(editTextPassword.getText());

        if (!login.equals("") && !pass.equals("")) {


            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {

                    String[] field = new String[2];
                    field[0] = "login";
                    field[1] = "pass";

                    String[] data = new String[2];
                    data[0] = login;
                    data[1] = pass;
                    PutData putData = new PutData("http://192.168.56.1/login/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            ArrayList<JSONObject> listItems;
                            String result = putData.getResult();
                            final JSONObject[] a = new JSONObject[1];
                            final String[] id = new String[1];
                            final String[] role = new String[1];
                            final String[] fam = new String[1];
                            final String[] name = new String[1];
                            final String[] email = new String[1];
                            final String[] number = new String[1];
                            try {
                                JSONObject object = new JSONObject(EncodingToUTF8(result));

                                JSONArray jsonarray = object.getJSONArray("users");
                                listItems = getArrayListFromJSONArray(jsonarray);
                                //t.setText(listItems.get(0).getString("idEUZ"));
                                id[0] = listItems.get(0).getString("idEUZ");
                                role[0] = listItems.get(0).getString("role");
                                fam[0] = listItems.get(0).getString("lastName");
                                name[0] = listItems.get(0).getString("firstName");
                                email[0] = listItems.get(0).getString("email");
                                number[0] = listItems.get(0).getString("numberphone");

                                //t.setText(role[0]);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (id[0]!=null) {
                                Intent intent = new Intent(getApplicationContext(), MainListApp.class);
                                startActivity(intent);
                                save("1", id[0], role[0], fam[0], name[0], email[0], number[0]);

                                finish();
                            } else {
                                save("0", "0", null, null, null, null, null);
                                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Не все обязательные поля заполнены", Toast.LENGTH_SHORT).show();
        }
    }
    public void save(String str, String id, String role, String fam, String name, String email, String number) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("check", str);
        myEdit.putString("id", id);
        myEdit.putString("role", role);
        myEdit.putString("fam", fam);
        myEdit.putString("name", name);
        myEdit.putString("email", email);
        myEdit.putString("number", number);
        myEdit.commit();
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
}
