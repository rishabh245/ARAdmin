package com.example.rishabh.aradmin.activities.nointernet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rishabh.aradmin.R;

public class NoInternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, NoInternetActivity.class);
        context.startActivity(starter);
    }
}
