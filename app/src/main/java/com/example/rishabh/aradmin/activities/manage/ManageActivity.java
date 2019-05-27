package com.example.rishabh.aradmin.activities.manage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.rishabh.aradmin.R;
import com.example.rishabh.aradmin.database.DatabaseClient;
import com.example.rishabh.aradmin.database.OpenHelper;
import com.example.rishabh.aradmin.model.AdDetails;
import com.example.rishabh.aradmin.model.ProfileAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageActivity extends AppCompatActivity implements ManageAdapter.AdClicked {


    private RecyclerView mRecyclerView;
    private ManageAdapter adapter;
    private ArrayList<ProfileAd> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        mRecyclerView = findViewById(R.id.manage_rv);
        arrayList = fetchDataFromDatabase();
        adapter = new ManageAdapter(this,this,arrayList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());





       /* FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dRef = firebaseDatabase.getReference().child("adDetails").
                child("Delhi");


        ValueEventListener adListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Get Ad Details Data and use the value to update UI
                for(DataSnapshot dSnapShot: dataSnapshot.getChildren()){
                    String adId = dSnapShot.getKey();
                    AdDetails adDetails = dSnapShot.getValue(AdDetails.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Error Occurred
                Log.w("loadAd:onCancelled", databaseError.toException() );
            }
        };

        dRef.addValueEventListener(adListener);

        */


    }



    private ArrayList<ProfileAd> fetchDataFromDatabase() {
        ArrayList<ProfileAd> arrayList = new ArrayList<>();
        SQLiteDatabase database = DatabaseClient.getWritableDatabase(this);
        Cursor cursor = database.query(OpenHelper.AD_DETAILS_TABLE_NAME,null, null,
                null,null,null,null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(OpenHelper.NAME));
            String address = cursor.getString(cursor.getColumnIndex(OpenHelper.ADDRESS));
            String price = cursor.getString(cursor.getColumnIndex(OpenHelper.PRICE));
            ProfileAd profileAdsDetails = new ProfileAd(name,address, price );
            arrayList.add(profileAdsDetails);
        }
        cursor.close();
        return arrayList;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ManageActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onDeleteButtonClicked(View view, int position) {

    }
}
