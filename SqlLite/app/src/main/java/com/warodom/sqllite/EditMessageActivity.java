package com.warodom.sqllite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.warodom.sqllite.model.Board;

public class EditMessageActivity extends AppCompatActivity {

    Board board;
    EditText edtTitle;
    EditText edtName;
    EditText edtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_message);

        board = getIntent().getParcelableExtra("BOARD");
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtTitle.setText(board.getTitle());
        edtName = (EditText) findViewById(R.id.edtName);
        edtName.setText(board.getName());
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        edtMessage.setText(board.getMessage());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Board newBoard = new Board(board.getId(),
                        edtTitle.getText().toString(),
                        edtName.getText().toString(),
                        edtMessage.getText().toString());

                Intent returnIntent = new Intent();
                returnIntent.putExtra("BOARD", newBoard );
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }

}
