package com.warodom.multiplefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag_blank_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View v) {
        getSupportFragmentManager()
                .beginTransaction()  // cannot make a global, and one-time commit() only
                .add(R.id.layout_fragment_container,
                        BlankFragment.newInstance(),
                        TAG )   // add TAG as fragment reference for remove purpose
                .addToBackStack(null) // null = not define stack name
                .commit();
    }

    public void remove(View v) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

    // ------ Button on BlankFragment --------
    public void closeFragment(View v) {
        System.out.println("Close");
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment != null) {
            getSupportFragmentManager().popBackStack();
        }

        // ====== save press Back button of Activity ====
        //    onBackPressed();
    }

    public void openOne(View v) {
        addFragment(OneFragment.newInstance());
    }

    public void openTwo(View v) {
        addFragment(TwoFragment.newInstance());
    }

    public void openThree(View v) {
        getSupportFragmentManager()
                .beginTransaction()  // cannot make a global, and one-time commit() only
                .replace(R.id.layout_fragment_container,  // or replace to overwrite (but keep track)
                        ThreeFragment.newInstance("My argument"),
                        TAG )   // add TAG as fragment reference for remove purpose
                .addToBackStack(null) // null = not define stack name
                .commit();
    }

    private void addFragment(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()  // cannot make a global, and one-time commit() only
                .replace(R.id.layout_fragment_container,  // or replace to overwrite (but keep track)
                        f,
                        TAG )   // add TAG as fragment reference for remove purpose
                .addToBackStack(null) // null = not define stack name
                .commit();
    }


}
