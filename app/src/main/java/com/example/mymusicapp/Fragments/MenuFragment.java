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
        fragmentArrayList.add(new SearchFragment());

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new PlaylistFragment());

        imageList =new int[]{R.drawable.ic_home,R.drawable.ic_search,R.drawable.ic_list,R.drawable.ic_folder};
        // Add title in array list
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");

        // Setup tab layout
        tabLayoutMenu.setupWithViewPager(viewPagerMenu);

        // Prepare view pager
        prepareViewPager(viewPagerMenu,fragmentArrayList,arrayList,tabLayoutMenu,imageList);
        return view;
    }

    private void prepareViewPager(ViewPager viewPagerMenu,ArrayList<Fragment> listFram ,ArrayList<String> arrayList,TabLayout tabLayoutMe, int[] imageList) {
        menuAdapter = new MenuAdapter(getActivity().getSupportFragmentManager());
        for (int i = 0; i< listFram.size(); i++){
            menuAdapter.addFragment(listFram.get(i),arrayList.get(i));
        }

        viewPagerMenu.setAdapter(menuAdapter);
        tabLayoutMe.setupWithViewPager(viewPagerMenu);
        for (int i =0 ; i< listFram.size(); i++) {
            tabLayoutMe.getTabAt(i).setIcon(imageList[i]);
        }

    }

}