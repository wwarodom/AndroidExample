package com.warodom.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);


        Glide.with(this)
                .load("http://goo.gl/gEgYUd")
//                .load("https://api.androidhive.info/images/glide/medium/deadpool.jpg")
                .override(500, 500) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .error( R.mipmap.ic_launcher_round ) // show this icon if image cannot load
//                .listener(new RequestListener<String, GlideDrawable>() { // during load
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        Log.e("ERROR", e.getMessage());
//                        return false;
//                    }
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        Log.e("ERROR","Resource Ready");
//                        return false;
//                    }
//                })
                .into(imageView);
   }
}
