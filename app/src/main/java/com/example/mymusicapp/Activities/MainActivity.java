package com.example.mymusicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mymusicapp.Fragments.MenuFragment;
import com.example.mymusicapp.R;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fragmentMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentMenu = findViewById(R.id.main_frame_layout);
        setFragment(new MenuFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentMenu.getId(), fragment);
        fragmentTransaction.commit();
    }
}