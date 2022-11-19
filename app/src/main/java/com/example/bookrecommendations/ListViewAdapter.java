package com.example.bookrecommendations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class ListViewAdapter extends ArrayAdapter< JSONObject> {
    int listLayout;
    ArrayList< JSONObject> usersList;
    Context context;
    public ListViewAdapter(Context context, int listLayout, ArrayList< JSONObject> usersList) {
        super(context, listLayout, usersList);
        this.context = context;
        this.listLayout=listLayout;
        this.usersList = usersList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(listLayout, null, false);
        TextView id = listViewItem.findViewById(R.id.id);
        TextView login = listViewItem.findViewById(R.id.loginn);
        try{
            id.setText(usersList.get(position).getString("idEUZ"));
            login.setText(usersList.get(position).getString("login"));
        }catch (JSONException je){
            je.printStackTrace();
        }
        return listViewItem;
    }

}
