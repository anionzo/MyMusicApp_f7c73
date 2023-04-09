package com.example.mymusicapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymusicapp.Adapters.SliderAdapter;
import com.example.mymusicapp.Models.SliderModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;


public class PlaylistFragment extends Fragment {

    private TextView nameListPlay;
    private RecyclerView listPlayItem;
    private ViewPager slider;
    private SliderAdapter sliderAdapter;

    private TextView seeMorePlaylist;
    private ArrayList<SliderModel> sliderModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        nameListPlay = view.findViewById(R.id.name_list_play);
        listPlayItem = view.findViewById(R.id.list_play_item);
        slider =view.findViewById(R.id.slider);
        seeMorePlaylist = view.findViewById(R.id.see_more_playlists);

        //
        sliderModel = new ArrayList<>();
        sliderModel.add(new SliderModel(R.drawable.poster1,"Vì yêu em"));
        sliderAdapter = new SliderAdapter(getContext(),sliderModel);
        slider.setAdapter(sliderAdapter);


        return view;
    }
}