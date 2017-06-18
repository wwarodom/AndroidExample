package com.warodom.customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] resId = { R.drawable.aerithgainsborough
                , R.drawable.barretwallace, R.drawable.caitsith
                , R.drawable.cidhighwind, R.drawable.cloudstrife
                , R.drawable.redxiii, R.drawable.sephiroth
                , R.drawable.tifalockhart, R.drawable.vincentvalentine
                , R.drawable.yuffiekisaragi, R.drawable.zackfair };

        String[] list = { "Aerith Gainsborough", "Barret Wallace", "Cait Sith"
                , "Cid Highwind", "Cloud Strife", "RedXIII", "Sephiroth"
                , "Tifa Lockhart", "Vincent Valentine", "Yuffie Kisaragi"
                , "ZackFair" };

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list, resId);

        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long row_id) {
                TextView textView = (TextView)view.findViewById(R.id.textView1);
                System.out.println( textView.getText());
                Toast.makeText(getApplicationContext(), "Clicked on item:" +  position , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
