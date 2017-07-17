package com.warodom.configurationchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    TODO: 2. replace by  static counter = 1
    int counter = 1;
    private TextView tvCounter;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = (TextView) findViewById(R.id.tvCounter);
        btnClick = (Button) findViewById(R.id.btnClick);

        tvCounter.setText("Counter: " + counter);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                tvCounter.setText("Counter: " + counter);
            }
        });
    }

//    // TODO: 3. override to pertain instance states
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("counter", counter);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        counter = savedInstanceState.getInt("counter");
//        tvCounter.setText("Counter: " + counter);
//    }
 }
