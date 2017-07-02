package com.warodom.sqllite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.warodom.sqllite.model.Board;
import com.warodom.sqllite.utils.DbHelper;

import java.util.ArrayList;

/**
 * Created by wwaro on 7/1/2017.
 */

class CustomAdapter  extends BaseAdapter {
    Context mContext;
    ArrayList<Board> boards;
    DbHelper mHelper;
    MainActivity mainActivity;

    public CustomAdapter(Context mContext, ArrayList<Board> boards) {
        this.mContext = mContext;
        this.boards = boards;
    }

    public CustomAdapter(Context mContext, ArrayList<Board> boards, DbHelper mHelper) {
        this.mContext = mContext;
        this.boards = boards;
        this.mHelper = mHelper;
    }

    public CustomAdapter(Context mContext, ArrayList<Board> boards, DbHelper mHelper, MainActivity mainActivity) {
        this.mContext = mContext;
        this.boards = boards;
        this.mHelper = mHelper;
        this.mainActivity = mainActivity;
    }

    public int getCount() {
        return boards.size();
    }

    public Object getItem(int position) {
        return boards.get(position);
    }

    public long getItemId(int position) {
        return boards.get(position).getId();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_row, parent, false);

        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvTitle.setText( "Id:" + boards.get(position).getId() + " " + boards.get(position).getTitle() );
        TextView tvName = (TextView)view.findViewById(R.id.tvName);
        tvName.setText( boards.get(position).getName() );
        TextView tvMessage = (TextView)view.findViewById(R.id.tvMessage);
        tvMessage.setText( boards.get(position).getMessage());

        Button btnGet = (Button ) view.findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                Board selectBoard = (Board) listView.getAdapter().getItem(position);
                Board b1 =  mHelper.getMessage(selectBoard.getId());
                Toast.makeText(mContext, b1.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnDelete = (Button ) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                Board selectBoard = (Board) listView.getAdapter().getItem(position);
                mHelper.deleteMessage( selectBoard.getId() );
                boards.remove( position );
                updateAdapter(boards);
            }
        });

        Button btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);
                Board selectBoard = (Board) listView.getAdapter().getItem(position);

                Intent intent = new Intent(mContext,EditMessageActivity.class);
                intent.putExtra("BOARD", selectBoard );
                mainActivity.startActivityForResult(intent,1);
            }
        });

        return view;
    }

    public void updateAdapter( ArrayList<Board> boards) {
        this.boards = boards;
        notifyDataSetChanged();
    }
}
