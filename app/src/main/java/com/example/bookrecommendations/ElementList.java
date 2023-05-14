package com.example.bookrecommendations;

import androidx.appcompat.app.AppCompatActivity;

public class ElementList  extends AppCompatActivity {
    int Image;
    String text;

    public ElementList(int image, String text) {
        Image = image;
        this.text = text;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
