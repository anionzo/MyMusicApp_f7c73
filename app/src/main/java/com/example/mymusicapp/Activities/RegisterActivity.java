package com.example.mymusicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mymusicapp.Fragments.HomeFragment;
import com.example.mymusicapp.Fragments.SignInFragment;
import com.example.mymusicapp.R;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    private void initView(){
        frameLayout = findViewById(R.id.register_frame_layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        setFragment(new SignInFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}