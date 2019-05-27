package com.example.rishabh.aradmin.activities.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.rishabh.aradmin.R;
import com.example.rishabh.aradmin.activities.Client;
import com.example.rishabh.aradmin.activities.manage.ManageActivity;
import com.example.rishabh.aradmin.activities.nointernet.NoInternetActivity;
import com.example.rishabh.aradmin.activities.post.PostAdActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int RC_SIGN_IN = 1;

    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.home_layout);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null && !user.isAnonymous()){
                    //User is Signed in
                    layout.setVisibility(View.VISIBLE);
                }
                else {
                    //User is Signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(
                                            Arrays.asList(
                                                    new AuthUI.IdpConfig.
                                                            Builder(AuthUI.EMAIL_PROVIDER).
                                                            build()
                                            ))
                                    .build(),
                            RC_SIGN_IN);

                }
            }
        };


        Button postAdButton = findViewById(R.id.button_post_ad);
        Button manageAdButton = findViewById(R.id.button_manage_ads);
        manageAdButton.setOnClickListener(this);
        postAdButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_post_ad){
            PostAdActivity.start(this);
        }
        else if(view.getId()==R.id.button_manage_ads){
            ManageActivity.start(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_SIGN_IN){
            if(resultCode == RESULT_OK){
                //User get successfully signed in
                layout.setVisibility(View.VISIBLE);
            }else {
                //No Internet Connection and arrives first time on app
                // Make an Activity which will show that there is bo Intenet Connection
                if(!Client.haveNetworkConnection(this)){
                    NoInternetActivity.start(this);
                    finish();
                }

            }
        }
    }

}
