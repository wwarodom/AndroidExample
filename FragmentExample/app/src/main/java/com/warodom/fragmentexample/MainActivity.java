package com.warodom.fragmentexample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBlankFragment();
        startJavaFragment();
    }

    private void startBlankFragment() {
        BlankFragment blankFragment = (BlankFragment) getSupportFragmentManager().findFragmentById(R.id.blankFragment );
        TextView tvf1 =  (TextView) blankFragment.getView().findViewById(R.id.tvf1);
        tvf1.setText("Blank fragment");
    }

    private void startJavaFragment() {
        JavaFragment fragment = new JavaFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.javaFragment, fragment);
        transaction.commit();
    }
}
