package com.warodom.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerViewAdapter adapter;

    @Override
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

        List<Actor> heroes = new ArrayList<Actor>();
        for(int i=0;i< list.length;i++)
            heroes.add(new Actor(list[i],resId[i]));

        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, heroes);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Click: " + adapter.getItem(position) +
                " on row:" + position, Toast.LENGTH_SHORT).show();
    }
}
