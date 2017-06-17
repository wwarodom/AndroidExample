package com.warodom.activityexample;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void send(View v) {
        Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
        String message = editText.getText().toString();
        intent.putExtra("message", message);
        intent.putExtra("isTrue", true);
        intent.putExtra("value", 5);

        Bundle bd = new Bundle();
        bd.putInt("value",1);
        bd.putString("message","Hello");
        intent.putExtra("bd",bd);

        intent.putExtra("MyData", new MyData());
        startActivity(intent);
    }
}

class MyData implements Parcelable {
    int x=1,y=2;

    public MyData() {}


    protected MyData(Parcel in) {
        x = in.readInt();
        y = in.readInt();
    }

    public static final Creator<MyData> CREATOR = new Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override   //used when packing
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
    }
}