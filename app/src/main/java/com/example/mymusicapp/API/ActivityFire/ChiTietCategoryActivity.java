package com.example.mymusicapp.API.ActivityFire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.mymusicapp.API.FragmetFire.ShowCategoryFireFragment;
import com.example.mymusicapp.Fragments.ShowListSongCategoryFragment;
import com.example.mymusicapp.R;

public class ChiTietCategoryActivity extends AppCompatActivity {
    private FrameLayout fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_category);
        fragmentList = findViewById(R.id.layout_chi_tiet_category);
        setFragment(new ShowCategoryFireFragment());
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentList.getId(), fragment);
        fragmentTransaction.commit();
    }
}