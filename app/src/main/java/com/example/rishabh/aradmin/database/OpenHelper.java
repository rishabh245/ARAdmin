package com.example.rishabh.aradmin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rishabh on 9/2/17.
 */

public class OpenHelper extends SQLiteOpenHelper {
    //USER INFO TABLE ATTRIBUTES
   /* public static final String USER_TABLE_NAME = "user";
    public static final String USER_NAME = "name";
    public static final String USER_NUMBER = "number";
    public static final String USER_UID = "uid";
    public static final String USER_PHOTO_URL = "photo_url";*/

    //USER SELL/BUY DETAILS TABLE ATTRIBUTES
    public static final String AD_DETAILS_TABLE_NAME="ad_details";
    public static final String AD_ID ="ad_id";
    public static final String NAME="name";
    public static final String ADDRESS="address";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lng";
    public static final String PRICE = "price";





    public OpenHelper(Context context) {
        super(context,"ARAdmin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String table = "CREATE TABLE "+AD_DETAILS_TABLE_NAME+" ( "+AD_ID+" text, "+NAME+" text, "+ADDRESS
                +" text, "+LATITUDE+" text, "+LONGITUDE+" text, "+PRICE+" text);" ;

        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}