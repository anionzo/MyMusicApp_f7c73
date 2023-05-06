package com.example.mymusicapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mymusicapp.Adapters.SliderAdapter;
import com.example.mymusicapp.Models.SliderModel;
import com.example.mymusicapp.R;
import com.example.mymusicapp.Utils.SliderTimer;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;


public class HomeFragment extends Fragment {


    private ViewPager slider;
    private ArrayList<SliderModel> slidersModelList;
    private SliderAdapter sliderAdapter;
    private TabLayout sliderIndicator;
    private Timer timer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // inttView
        slider =view.findViewById(R.id.slider);
        sliderIndicator = view.findViewById(R.id.slider_indicator);

        // slider

        timer = new Timer();

        slidersModelList = new ArrayList<>();

        slidersModelList.add(new SliderModel(getString(R.string.url_img), "Đôi Mắt"));
        slidersModelList.add(new SliderModel(getString(R.string.url_img1), "Vĩnh Hằng"));
        slidersModelList.add(new SliderModel(getString(R.string.url_img2), "Vũ Nhạc"));
        slidersModelList.add(new SliderModel(getString(R.string.url_img3), "Mèo Mướp"));

        sliderAdapter = new SliderAdapter(getContext(),slidersModelList);
        slider.setAdapter(sliderAdapter);

        sliderIndicator.setupWithViewPager(slider);
        timer.scheduleAtFixedRate(new SliderTimer(getActivity(),slider,slidersModelList.size()),4000,6000);
        // playListt

        return view;
    }

}