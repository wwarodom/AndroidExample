package com.warodom.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    final String APP = "APP_PREFERENCES";

    Button btnShared;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSharedPreferences();

        btnShared = (Button)findViewById(R.id.button);
        btnShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read from SharedPreferences, if KEY is not defined, print "Default string"
                String str = sp.getString("KEY","Default String");
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                File f = getDatabasePath(APP + ".xml");
                System.out.println(  f.getAbsolutePath());
            }
        });
    }

    private void createSharedPreferences() {
        sp = getSharedPreferences(APP, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("KEY","Foo Bar");
        editor.putBoolean("LOGIC", true);
        editor.commit();
    }
}
