package com.example.bookrecommendations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView listView;
    SearchView searchView;

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    public NotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        listView=(ListView) rootView.findViewById(R.id.list);
        searchView=(SearchView) rootView.findViewById(R.id.search);
        Button button = rootView.findViewById(R.id.add);
        arrayAdapter=new ArrayAdapter<String>(getActivity(), R.layout.item, arrayList);
        listView.setAdapter(arrayAdapter);
        TextView text=rootView.findViewById(R.id.text);
        button.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =null;
                intent = new Intent(getActivity(), NoteNew.class);


                startActivity(intent);
            }
        });

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            String id = sharedPreferences.getString("id", "");
            @Override
            public void run() {

                String[] field = new String[1];
                field[0] = "id";


                String[] data = new String[1];
                data[0] = id;
                PutData putData = new PutData("http://192.168.56.1/login/listNotes.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        ArrayList<JSONObject> listItems;
                        String result = putData.getResult();
                        final String[] title = new String[100];
                        final String[] textNote = new String[100];
                        final String[] id_note = new String[100];

                        try {
                            JSONObject object = new JSONObject(EncodingToUTF8(result));
                            JSONArray jsonarray = object.getJSONArray("data");
                            listItems = getArrayListFromJSONArray(jsonarray);
                            int t= listItems.size();
                            //title[0] = listItems.get(0).getString("name_note");
                            for(int i=0;i<listItems.size();i++) {
                                title[i] = listItems.get(i).optString("name_note");
                                textNote[i] = listItems.get(i).optString("text_note");
                                id_note[i] = listItems.get(i).optString("id_note");
                                //text.setText(listItems.toString());
                                if(textNote[i].length()<=30)
                                    arrayList.add(title[i]+"\n"+textNote[i]);
                                else
                                    arrayList.add(title[i]+"\n"+textNote[i].substring(0, 30)+"...");
                                listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> arg0, View view, int i, long arg3) {
                                        Intent intent =null;
                                        intent = new Intent(getActivity(), Note.class);
                                        intent.putExtra("name_note", title[i]);
                                        intent.putExtra("text_note", textNote[i]);
                                        intent.putExtra("id_note", id_note[i]);
                                        startActivity(intent);
                                    }
                                });
                                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String s) {
                                        arrayAdapter.getFilter().filter(s);
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String s) {
                                        arrayAdapter.getFilter().filter(s);
                                        return false;
                                    }
                                });

                            }
                            arrayAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            text.setText(e.toString());
                        }

                    }
                }
            }
        });

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