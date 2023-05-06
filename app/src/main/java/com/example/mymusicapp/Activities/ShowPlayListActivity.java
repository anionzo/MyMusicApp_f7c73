package com.example.mymusicapp.Activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymusicapp.Fragments.ShowPlayListFragment;
import com.example.mymusicapp.R;

public class ShowPlayListActivity extends AppCompatActivity {

    private FrameLayout fragmentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_play_list);
        fragmentMenu = findViewById(R.id.show_play_list_frame_layout);
        setFragment(new ShowPlayListFragment());
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentMenu.getId(), fragment);
        fragmentTransaction.commit();
    }
}