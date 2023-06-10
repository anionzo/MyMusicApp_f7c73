package com.example.mymusicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mymusicapp.API.FragmetFire.ListCategoryFireFragment;
import com.example.mymusicapp.R;

public class ShowLisrCategoryActivity extends AppCompatActivity {
    private FrameLayout fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lisr_category);
        fragmentList = findViewById(R.id.layout_show_list_category);
        setFragment(new ListCategoryFireFragment());
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentList.getId(), fragment);
        fragmentTransaction.commit();
    }
}