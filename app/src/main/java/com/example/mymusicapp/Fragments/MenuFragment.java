package com.example.mymusicapp.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusicapp.Adapters.MenuAdapter;
import com.example.mymusicapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MenuFragment extends Fragment {

    private TabLayout tabLayoutMenu;
    private ViewPager viewPagerMenu;
    private MenuAdapter menuAdapter;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();

    private   int[] imageList = new int[10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        tabLayoutMenu = view.findViewById(R.id.tab_layout_menu);
        viewPagerMenu = view.findViewById(R.id.view_pager_menu);

        ArrayList<String> arrayList=new ArrayList<>(0);

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new SignInFragment());

        imageList =new int[]{R.drawable.ic_home,R.drawable.ic_error};
        // Add title in array list
        arrayList.add("Home");
        arrayList.add("login");


        // Setup tab layout
        tabLayoutMenu.setupWithViewPager(viewPagerMenu);

        // Prepare view pager
        prepareViewPager(viewPagerMenu,arrayList);
        return view;
    }

    private void prepareViewPager(ViewPager viewPagerMenu, ArrayList<String> arrayList) {
        menuAdapter = new MenuAdapter(getActivity().getSupportFragmentManager());
        menuAdapter.addFragment(new HomeFragment(),arrayList.get(0));
        menuAdapter.addFragment(new SignInFragment(),arrayList.get(1));

        viewPagerMenu.setAdapter(menuAdapter);
        tabLayoutMenu.setupWithViewPager(viewPagerMenu);
        tabLayoutMenu.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayoutMenu.getTabAt(1).setIcon(R.drawable.ic_error);
    }

}