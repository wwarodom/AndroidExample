package com.warodom.thread1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvNum;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNum = (TextView) findViewById(R.id.tvNum);
        new MyTask().execute("My String");

    }


    class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // get the string from params, which is an array
            String myString = params[0];
            while( i < 5 )
            try {
                Thread.sleep(1000);
                System.out.println(i++);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "pass to onPostExecute: " + i;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvNum.setText(String.valueOf(values[0]));
        }
    }
}