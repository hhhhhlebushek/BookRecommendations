package com.example.bookrecommendations;

import static com.example.bookrecommendations.R.drawable.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElementAdapter  extends ArrayAdapter<ElementList> {
    int Image;
    String text;
    private Context mContext;
    private int mResource;

    public ElementAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ElementList> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        convertView=layoutInflater.inflate(mResource, parent, false);
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView text = convertView.findViewById(R.id.textAll);
        imageView.setImageResource(getItem(position).getImage());
        text.setText(getItem(position).getText());

        return convertView;
    }
}
