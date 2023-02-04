package com.example.bookrecommendations;

import static android.media.CamcorderProfile.get;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendationsFragment extends Fragment {
    public static ArrayList<String> arrayList = new ArrayList<String>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RecommendationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendationsFragment newInstance(String param1, String param2) {
        RecommendationsFragment fragment = new RecommendationsFragment();
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

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] mas = arrayList.toArray(new String[arrayList.size()]);
        View rootView = inflater.inflate(R.layout.fragment_recommendations, container, false);
        ArrayList<CheckBox> boxes = new ArrayList<CheckBox>();

        EditText name = rootView.findViewById(R.id.name);
        EditText author = rootView.findViewById(R.id.author);
        EditText year = rootView.findViewById(R.id.year);
        EditText genre = rootView.findViewById(R.id.genre);
        final String newName, newAuthor, newYear, newGenre;
        newName = String.valueOf(name.getText());
        newAuthor = String.valueOf(author.getText());
        newYear = String.valueOf(year.getText());
        newGenre = String.valueOf(genre.getText());

        // Get the reference of movies
        //ListView moviesList=(ListView)rootView.findViewById(R.id.list);
        String[] countries = new String[]{
                "India",
                "Pakistan",
                "Sri Lanka",
                "China",
                "Bangladesh",
                "Nepal",
                "Afghanistan",
                "North Korea",
                "South Korea",
                "Japan"
        };
        // The checkbox for the each item is specified by the layout android.R.layout.simple_list_item_multiple_choice
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.color_text, countries);
        Button button = rootView.findViewById(R.id.button);
        // Getting the reference to the listview object of the layout
        ListView listView = (ListView)rootView.findViewById(R.id.list);

        // Setting adapter to the listview
        listView.setAdapter(adapter);
        listView.setBackgroundColor(Color.WHITE);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                 int cntChoice = listView.getCount();
                String checked = "";
                String unchecked = "";
                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                    if (sparseBooleanArray.get(position) == true) {
                        arrayList.add(listView.getItemAtPosition(position).toString());
                        //Toast.makeText(getContext(), listView.getItemAtPosition(i).toString(),   Toast.LENGTH_LONG).show();
                        //checked += listView.getItemAtPosition(i).toString()+ "\n";

                    } else if (sparseBooleanArray.get(position) == false) {
                        arrayList.remove(listView.getItemAtPosition(position).toString());
                    }
                }

        });
        button.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/rubik_extra_bold.ttf"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (name.equals(null))
                    Toast.makeText(getActivity(), "НЕУспех"+String.valueOf(name.getText()), Toast.LENGTH_SHORT).show();
                else  Toast.makeText(getActivity(), "Успех "+String.valueOf(name.getText()), Toast.LENGTH_SHORT).show();*/

                if (!String.valueOf(name.getText()).equals("") && !String.valueOf(author.getText()).equals("") && !String.valueOf(year.getText()).equals("") && !String.valueOf(genre.getText()).equals(""))
                {
                //Toast.makeText(getContext(), arrayList.toString(),   Toast.LENGTH_LONG).show();
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[5];
                            field[0] = "name";
                            field[1] = "author";
                            field[2] = "year";
                            field[3] = "genre";
                            field[4] = "words";

                            String[] data = new String[5];
                            data[0] = String.valueOf(name.getText());
                            data[1] = String.valueOf(author.getText());
                            data[2] = String.valueOf(year.getText());
                            data[3] = String.valueOf(genre.getText());
                            data[4] = arrayList.toString();
                            PutData putData = new PutData("http://192.168.56.1/tests/gettingRecommendations.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {

                                    String result = putData.getResult();

                                    if (result.equals("Success")) {
                                        Toast.makeText(getActivity(), "Успех", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else
                Toast.makeText(getActivity(), "Заполните все обязательные поля (название, автор, год и жанр книги)!", Toast.LENGTH_SHORT).show();
            }
        });
        Log.d(LOG_TAG, "itemSelect: "
                + arrayList.toString());
        /*ArrayList<String> movies = new ArrayList<String>();

        movies.add("X-Men");
        movies.add("IRONMAN");
        movies.add("SPIDY");
        movies.add("NARNIA");
        movies.add("LIONKING");
        movies.add("AVENGERS");

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.color_text, movies);
        // Set The Adapter
        moviesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                Toast.makeText(getContext(), "Movie Selected : ",   Toast.LENGTH_LONG).show();
                //String selectedmovie=movies.get(position);
                //Toast.makeText(getContext(), "Movie Selected : "+movies.get(position),   Toast.LENGTH_LONG).show();
            }
        });



        for (int i = 0; i<boxes.size(); i++){
            CheckBox cb = boxes.get(i);
            if (cb.isChecked()){
                System.out.println("checked");
                //from here you can populate you array
            }else{
                System.out.println("not checked");
                //from here you can populate you array
            }
        }*/
        arrayList.clear();
        return rootView;
    }
}

