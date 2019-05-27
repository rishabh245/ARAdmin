package com.example.rishabh.aradmin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rishabh on 9/2/17.
 */

public class DatabaseClient {
    private  static OpenHelper openHelper ;
    private  static SQLiteDatabase readableDatabase;
    private  static SQLiteDatabase writableDatabase;

    public static OpenHelper getOpenHelper(Context context) {
        if(openHelper==null){
            openHelper = new OpenHelper(context);
        }
        return openHelper;
    }

    public static SQLiteDatabase getReadableDatabase(Context context) {
        if(readableDatabase==null){
            readableDatabase = getOpenHelper(context).getReadableDatabase();
        }
        return readableDatabase;
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        if(writableDatabase==null){
            writableDatabase = getOpenHelper(context).getWritableDatabase();
        }
        return writableDatabase;
    }
}

