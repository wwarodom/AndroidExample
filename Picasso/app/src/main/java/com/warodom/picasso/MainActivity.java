package com.warodom.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .resize(1000, 1000)
                .centerCrop()
                .into(imageView);
    }
}
