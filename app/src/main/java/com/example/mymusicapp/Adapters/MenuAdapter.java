package com.example.mymusicapp.Adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mymusicapp.Fragments.MenuFragment;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class MenuAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
    ArrayList<String> stringArrayList=new ArrayList<>();
    Context context;

    public MenuAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }



    // Create constructor
    public void addFragment(Fragment fragment,String s)
    {
        // Add fragment
        fragmentArrayList.add(fragment);
        // Add title
        stringArrayList.add(s);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }
}
