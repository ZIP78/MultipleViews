package com.assessment.paulkim.codeauthassess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    private String stringData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = findViewById(R.id.image);

        Intent receiveUrl = getIntent();
        //receiving the url from EditText
        stringData = receiveUrl.getStringExtra("url_of_pic");
        //Displaying the pic using Glide
        Glide.with(ImageActivity.this)
                .load(stringData)
                .into(imageView);
    }

}
