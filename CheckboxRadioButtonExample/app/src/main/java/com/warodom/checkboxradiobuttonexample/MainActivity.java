package com.warodom.checkboxradiobuttonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    CheckBox checkbox_meat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbox_meat = (CheckBox) findViewById(R.id.checkbox_meat);
        checkbox_meat.setChecked(true); // checked
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_meat:
                if (checked)
                    System.out.println("Put some meat on the sandwich");
                else
                    System.out.println("Remove the meat");
                break;
            case R.id.checkbox_cheese:
                if (checked)
                    System.out.println("Cheese me");
                else
                    System.out.println("I'm lactose intolerant");
                break;
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    System.out.println("Pirates are the best");
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    System.out.println("Ninjas rule ");
                    break;
        }
    }
}
