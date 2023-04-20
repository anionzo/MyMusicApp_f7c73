package com.example.mymusicapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mymusicapp.Fragments.ShowCategoryFragment;
import com.example.mymusicapp.R;

public class ShowListActivity extends AppCompatActivity {

    private FrameLayout fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        fragmentList = findViewById(R.id.show_list_frame_layout);
        setFragment(new ShowCategoryFragment());
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentList.getId(), fragment);
        fragmentTransaction.commit();
    }
}