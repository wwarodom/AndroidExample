package com.warodom.sqllite.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wwaro on 7/1/2017.
 */

public class Board implements Parcelable {

    public static final String TABLE_NAME = "Board";
    public static final String COL_TITLE = "title";
    public static final String COL_NAME = "name";
    public static final String COL_MESSAGE = "message";

    private int id;
    private String title;
    private String name;
    private String message;

    public Board() {
    }

    public Board(String title, String name, String message) {
        this.title = title;
        this.name = name;
        this.message = message;
    }

    public Board(int id, String title, String name, String message) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.message = message;
    }

    protected Board(Parcel in) {
        id = in.readInt();
        title = in.readString();
        name = in.readString();
        message = in.readString();
    }

    public static final Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };

    //  =====  Getter / Setter ===
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return  "Id: " + id + "\nTile: " + title + "\nName: "+ name + "\nMessage: " + message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(message);
    }
}
