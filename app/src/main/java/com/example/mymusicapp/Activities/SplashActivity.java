package com.example.mymusicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mymusicapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }else {
                    intent = new Intent(SplashActivity.this, RegisterActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}