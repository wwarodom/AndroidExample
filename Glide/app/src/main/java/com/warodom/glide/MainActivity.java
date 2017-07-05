package com.warodom.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        Glide.with(this)
                .load("http://goo.gl/gEgYUd")
                .override(500, 500) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .into(imageView);
   }
}
