package com.example.bookrecommendations;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Button buttonOutPut;
        buttonOutPut = rootView.findViewById(R.id.buttonOutPut);
        buttonOutPut.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/rubik_extra_bold.ttf"));
        buttonOutPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setMessage("Вы действительно хотите выйти?");
                builder.setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit().clear();
                                editor.commit();

                                Intent i = new Intent(getActivity(), LogIn.class);
                                startActivity(i);
                                getActivity().finish();
                            }
                        });
                builder.setNegativeButton("Нет",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.setCancelable(false);

                builder.create().show();
            }// end onClick
        });
        LinearLayout constraintLayout;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String role = sharedPreferences.getString("role", "");

            Button bt_setting = new Button(getContext());
            bt_setting.setText("Обновление личных данных");
            bt_setting.setBackgroundResource(R.drawable.btn_item_in_profile);
            bt_setting.setWidth(850);
            bt_setting.setHeight(200);
            bt_setting.setTextColor(Color.parseColor("#F38269"));
            bt_setting.setLeftTopRightBottom(20, 20, 0, 0);
            bt_setting.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/rubik_extra_bold.ttf"));
            bt_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), UpdatePersonalData.class);
                    startActivity(intent);

                }// end onClick
            });
            Button bt_EUZ = new Button(getContext());
            bt_EUZ.setText("Обновление данных входа");
            bt_EUZ.setBackgroundResource(R.drawable.btn_item_in_profile);
            bt_EUZ.setWidth(850);
            bt_EUZ.setHeight(200);
            bt_EUZ.setY(70);
            bt_EUZ.setTextColor(Color.parseColor("#F38269"));
            bt_EUZ.setLeftTopRightBottom(20, 20, 0, 0);
            bt_EUZ.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/rubik_extra_bold.ttf"));
            bt_EUZ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), UpdateDataEUZ.class);
                    startActivity(intent);

                }// end onClick
            });

            constraintLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
            constraintLayout.addView(bt_setting);
            //constraintLayout.addView(rootView.createRigidArea(new Dimension(5, 0)));
            constraintLayout.addView(bt_EUZ);
        Button bt_rec = new Button(getContext());
        if(role.equals("admin")) {
            Button bt_add_test = new Button(getContext());

            bt_add_test.setText("Добавить тест");
            bt_add_test.setBackgroundResource(R.drawable.btn_item_in_profile);
            bt_add_test.setWidth(850);
            bt_add_test.setHeight(200);
            bt_add_test.setY(140);
            bt_add_test.setTextColor(Color.parseColor("#F38269"));
            bt_add_test.setLeftTopRightBottom(20, 100, 0, 0);
            bt_add_test.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/rubik_extra_bold.ttf"));
            bt_add_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AddTest.class);
                    startActivity(intent);

                }// end onClick
            });
            bt_rec.setText("Обработка рекомендаций");
            bt_rec.setBackgroundResource(R.drawable.btn_item_in_profile);
            bt_rec.setWidth(850);
            bt_rec.setHeight(200);
            bt_rec.setY(210);
            bt_rec.setTextColor(Color.parseColor("#F38269"));
            bt_rec.setLeftTopRightBottom(20, 100, 0, 0);
            bt_rec.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/rubik_extra_bold.ttf"));
            bt_rec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ProcessingRecommendations.class);
                    startActivity(intent);

                }// end onClick
            });
            constraintLayout.addView(bt_add_test);
            constraintLayout.addView(bt_rec);
        }
        if(role.equals("moderator")) {
            bt_rec.setText("Обработка рекомендаций");
            bt_rec.setBackgroundResource(R.drawable.btn_item_in_profile);
            bt_rec.setWidth(850);
            bt_rec.setHeight(200);
            bt_rec.setY(140);
            bt_rec.setTextColor(Color.parseColor("#F38269"));
            bt_rec.setLeftTopRightBottom(20, 100, 0, 0);
            bt_rec.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/rubik_extra_bold.ttf"));
            bt_rec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ProcessingRecommendations.class);
                    startActivity(intent);

                }// end onClick
            });
            constraintLayout.addView(bt_rec);
        }

        return rootView;
    }
}