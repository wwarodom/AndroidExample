package com.warodom.picassogrid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

// ==================================================================
// Sample from DevAHoy:  https://devahoy.com/posts/android-picasso-tutorial/

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] urls = initSampleData();

        mGridView = (GridView) findViewById(R.id.gridView);
        mAdapter = new CustomAdapter(this, urls);
        mGridView.setAdapter(mAdapter);
    }

    private String[] initSampleData() {
        return new String[]{"http://i.dailymail.co.uk/i/pix/2016/04/12/23/3319F89C00000578-3536787-image-m-19_1460498410943.jpg",
                "https://www.w3schools.com/css/img_forest.jpg",
                "https://www.w3schools.com/css/trolltunga.jpg",
                "https://www.w3schools.com/css/pineapple.jpg",
                "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
                "https://www.w3schools.com/css/paris.jpg",
                "https://www.w3schools.com/css/paris.jpg",
                "https://www.w3schools.com/css/trolltunga.jpg",
                "https://www.w3schools.com/css/lights600x400.jpg",
                "http://icons.iconarchive.com/icons/aroche/delta/256/File-JPG-icon.png",
                "http://icons.iconarchive.com/icons/ampeross/lamond/256/jpg-icon.png",
                "http://icons.iconarchive.com/icons/iynque/flat-ios7-style-documents/256/jpg-icon.png"
        };
    }
}
