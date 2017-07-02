package com.warodom.sqllite.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.warodom.sqllite.model.Board;

import java.util.ArrayList;

import static com.warodom.sqllite.model.Board.COL_MESSAGE;
import static com.warodom.sqllite.model.Board.COL_NAME;
import static com.warodom.sqllite.model.Board.COL_TITLE;
import static com.warodom.sqllite.model.Board.TABLE_NAME;

/**
 * Created by wwaro on 7/1/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Board.db";
    private static final int DB_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                +"  (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TITLE + " TEXT, " + COL_NAME + " TEXT, "
                + COL_MESSAGE + " TEXT);");
        db.execSQL("INSERT INTO " + TABLE_NAME
                + " (" + COL_TITLE + ", " + COL_NAME  + ", " + COL_MESSAGE
                + ") VALUES ('Asking for List', 'Warodom', 'Test hello world message 1!');");
        db.execSQL("INSERT INTO " + TABLE_NAME
                + " (" + COL_TITLE + ", " + COL_NAME  + ", " + COL_MESSAGE
                + ") VALUES ('Android programming', 'John', 'Test android message 2!');");
        db.execSQL("INSERT INTO " + TABLE_NAME
                + " (" + COL_TITLE + ", " + COL_NAME  + ", " + COL_MESSAGE
                + ") VALUES ('Beware the color bugs', 'Jack', 'Test the last initial message 3!');");
        Log.d("MyDB","Create Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.d("MyDB","Re-Create Table");
    }


    public ArrayList<Board> getAllMessages() {
        ArrayList <Board> boards = new ArrayList<Board>();
        SQLiteDatabase mDb = getWritableDatabase();
        Cursor mCursor = mDb.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        mCursor.moveToFirst();
        while ( !mCursor.isAfterLast() ){
            int id        = mCursor.getInt((mCursor.getColumnIndex("_id")));
            String title  = mCursor.getString(mCursor.getColumnIndex(COL_TITLE));
            String name   = mCursor.getString(mCursor.getColumnIndex(COL_NAME));
            String message= mCursor.getString(mCursor.getColumnIndex(COL_MESSAGE));
            boards.add(new Board(id,title,name,message) );
            mCursor.moveToNext();
        }
        return boards;
    }

    public Board getMessage(int id) {
        SQLiteDatabase mDb = getWritableDatabase();
        Board board = new Board();

        Cursor mCursor = mDb.query( TABLE_NAME, null,
                " _id = ? ", new String[] { String.valueOf(id) } ,
                null,null, null,null);

        mCursor.moveToFirst();
        board.setId( mCursor.getInt((mCursor.getColumnIndex("_id"))));
        board.setTitle(mCursor.getString(mCursor.getColumnIndex(COL_TITLE)));
        board.setName(mCursor.getString(mCursor.getColumnIndex(COL_NAME)));
        board.setMessage(mCursor.getString(mCursor.getColumnIndex(COL_MESSAGE)));
        return board;
    }

    public void addMessage(Board board) {
        SQLiteDatabase mDb = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, board.getTitle());
        values.put(COL_NAME, board.getName());
        values.put(COL_MESSAGE, board.getMessage());
        mDb.insert(TABLE_NAME, null, values);
        mDb.close();
    }

    public void deleteMessage(int id) {
        SQLiteDatabase mDb = getWritableDatabase();
        /* mDb.delete(TABLE_NAME, " _id = ? ",
                new String[] { String.valueOf(board.getId()) });*/
        mDb.delete(TABLE_NAME, "_id = " + id, null);
        mDb.close();
    }

    public void updateMessage(Board board) {
        SQLiteDatabase mDb  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, board.getTitle());
        values.put(COL_NAME, board.getName());
        values.put(COL_MESSAGE, board.getMessage());
        int row = mDb.update(TABLE_NAME,
                values, " _id = ? ",
                new String[] { String.valueOf(board.getId()) });
        mDb.close();
    }
}
