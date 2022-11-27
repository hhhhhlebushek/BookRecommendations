package com.example.bookrecommendations;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView listView;

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    public TestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestsFragment newInstance(String param1, String param2) {
        TestsFragment fragment = new TestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tests, container, false);
        listView=(ListView) rootView.findViewById(R.id.list);
        arrayAdapter=new ArrayAdapter<String>(getActivity(), R.layout.item, arrayList);
        listView.setAdapter(arrayAdapter);
        TextView t=rootView.findViewById(R.id.text);
        //final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        //progressBar.setVisibility(ListView.VISIBLE);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                PutDataNew putData = new PutDataNew("http://192.168.56.1/tests/parsingNameTests.php", "POST");
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        ArrayList<JSONObject> listItems;
                        String result = putData.getResult();

                        final JSONObject[] a = new JSONObject[1];
                        final String[] title = new String[100];
                        final String loc="l";
                        //progressBar.setVisibility(View.INVISIBLE);
                        try {   JSONObject object = new JSONObject(EncodingToUTF8(result));
                            JSONArray jsonarray = object.getJSONArray("nameTests");
                            //JSONArray jsonarray = object.toJSONArray(object.names());

                            listItems = getArrayListFromJSONArray(jsonarray);
                            //t.setText(listItems.get(0).getString("1"));
                            for(int i=0;i<listItems.size();i++) {
                                title[i] = listItems.get(i).getString(getName(i+1));
                                arrayList.add(title[i]);
                                listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> arg0, View view, int i, long arg3) {
                                        Intent intent =null;

                                        switch (i)
                                        {
                                            case 0:
                                                intent = new Intent(getActivity(), QuizeActivity.class);
                                                intent.putExtra("nameTest", title[i]);
                                                break;
                                            case 1:
                                                intent = new Intent(getActivity(), LogIn.class);break;
                                            default: intent = new Intent(getActivity(), SignUp.class);break;
                                        }
                                        startActivity(intent);
                                    }
                                });
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
        //String value = "field(id_product,name,price)";


        return rootView;
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