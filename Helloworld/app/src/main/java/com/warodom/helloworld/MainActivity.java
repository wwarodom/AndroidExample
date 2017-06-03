package com.warodom.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView edtInput;
    Button btnSend;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInput = (TextView) findViewById(R.id.edtInput);
        btnSend = (Button) findViewById(R.id.btnSend);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
    }

    public void sendMessage(View v) {
        tvOutput.setText(  tvOutput.getText() + " " + edtInput.getText() );
    }
}
