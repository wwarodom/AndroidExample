package com.warodom.activityexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Write a class implements parceable

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
        Toast.makeText(this, "Name : " + message, Toast.LENGTH_SHORT).show();
        Bundle bundle = getIntent().getExtras();
        System.out.println( bundle.getInt("value")   );
        System.out.println( bundle.getBoolean("isTrue",false)   );
        System.out.println( bundle.getString("message") );

        Bundle bd = getIntent().getBundleExtra("bd");
        System.out.println( bd.getInt("value")   );
        System.out.println( bd.getString("message") );

        MyData md = getIntent().getParcelableExtra("MyData");
        System.out.println("x = " + md.x + "\n y = " + md.y );

//        System.out.println( intent.getIntExtra("value",1)   );
//        System.out.println( intent.getBooleanExtra("isTrue",false)   );
    }

    public void quit(View v) {
        finish(); // destroy current activity
    }
}
