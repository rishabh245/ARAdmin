package com.example.rishabh.aradmin.activities.post;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rishabh.aradmin.R;
import com.example.rishabh.aradmin.database.DatabaseClient;
import com.example.rishabh.aradmin.database.OpenHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostAdActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submitButton;
    private EditText et_name;
    private EditText et_address;
    private EditText et_lat;
    private EditText et_lng;
    private EditText et_price;

    public static void start(Context context) {
        Intent starter = new Intent(context, PostAdActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad);
        bindViews();
        submitButton.setOnClickListener(this);
    }

    private void bindViews() {
        submitButton = findViewById(R.id.bn_submit);
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_lat = findViewById(R.id.et_lat);
        et_lng = findViewById(R.id.et_lng);
        et_price = findViewById(R.id.et_price);
    }

    @Override
    public void onClick(View view) {
        String name = "";
        String address = "";
        String latitude = "";
        String longitude = "";
        String price = "";
        if(et_name!=null && et_name.getText()!=null){
            name = et_name.getText().toString();
        }
        if(et_address!=null && et_address.getText()!=null){
            address = et_address.getText().toString();
        }
        if(et_lat!=null && et_lat.getText()!=null){
            latitude = et_lat.getText().toString();
        }
        if(et_lng!=null && et_lng.getText()!=null){
            longitude = et_lng.getText().toString();
        }
        if(et_price!=null && et_price.getText()!=null){
            price = et_price.getText().toString();
        }

        if(name.equals("") ){
            Toast.makeText(this, "Name Cannot be Empty", Toast.LENGTH_SHORT).show();
        }

        if(address.equals("") ){
            Toast.makeText(this, "Address Cannot be Empty", Toast.LENGTH_SHORT).show();
        }

        if(latitude.equals("") ){
            Toast.makeText(this, "Latitude Cannot be Empty", Toast.LENGTH_SHORT).show();
        }
        if(longitude.equals("") ){
            Toast.makeText(this, "Longitude Cannot be Empty", Toast.LENGTH_SHORT).show();
        }

        if(price.equals("") ){
            Toast.makeText(this, "Price Cannot be Empty", Toast.LENGTH_SHORT).show();
        }

        insertIntoFirebase(name, address, latitude, longitude, price);


    }

    private void insertIntoFirebase(String name, String address,
                                    String latitude, String longitude, String price) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            //display message that user is logged out.
            Toast.makeText(this, "You are logged out. Login Again!! ",Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Processing..");
        mDialog.setCancelable(false);
        mDialog.show();
        //Create an instance of Firebase Realtime Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Get Reference of node where we want to write data
        DatabaseReference dRef = firebaseDatabase.getReference().child("adDetails").
                child("Delhi");
        String adId = dRef.push().getKey();
        DatabaseReference adRef = dRef.child(adId);
        adRef.child("adId").setValue(adId);
        adRef.child("customerName").setValue(name);
        adRef.child("customerAddress").setValue(address);
        adRef.child("Latitude").setValue(latitude);
        adRef.child("Longitude").setValue(longitude);
        adRef.child("price").setValue(price);
        adRef.child("admin").setValue(user.getUid());

        insertIntoLocalDatabase(adId, name, address, latitude, longitude, price );

        if(mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
            finish();
        }

        /**************
         *
         *
         *
         */


    }

    private void insertIntoLocalDatabase(String adId, String name, String address, String latitude, String longitude, String price) {
        SQLiteDatabase db = DatabaseClient.getWritableDatabase(this);
        ContentValues cv = new ContentValues();
        cv.put(OpenHelper.AD_ID , adId);
        cv.put(OpenHelper.NAME , name);
        cv.put(OpenHelper.ADDRESS, address);
        cv.put(OpenHelper.LATITUDE , latitude);
        cv.put(OpenHelper.LONGITUDE , longitude);
        cv.put(OpenHelper.PRICE , price);
        db.insert(OpenHelper.AD_DETAILS_TABLE_NAME , null , cv);
    }
}
