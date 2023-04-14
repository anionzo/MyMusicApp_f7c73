package com.example.mymusicapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymusicapp.Adapters.SliderAdapter;
import com.example.mymusicapp.Adapters.SongAdapter;
import com.example.mymusicapp.Models.SliderModel;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;


public class PlaylistFragment extends Fragment {

    private TextView nameListPlay;
    private RecyclerView recyclerViewPlayItem;
    private ViewPager slider;
    private SliderAdapter sliderAdapter;
    private SongAdapter songAdapter;
    private ArrayList<SongModel> songModelArrayList;
    //private TextView seeMorePlaylist;
    private ArrayList<SliderModel> sliderModel;
    LinearLayoutManager layoutManager;

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
        recyclerViewPlayItem = view.findViewById(R.id.list_play_item);
        slider =view.findViewById(R.id.slider);
        //seeMorePlaylist = view.findViewById(R.id.see_more_playlists);


        sliderModel = new ArrayList<>();
        // Set IMG
        sliderModel.add(new SliderModel(R.drawable.poster1,"Vì yêu em"));
        sliderAdapter = new SliderAdapter(getContext(),sliderModel);
        slider.setAdapter(sliderAdapter);


        songAdapter = new SongAdapter(getContext(),songModelArrayList);
        layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);


        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewPlayItem.setLayoutManager(layoutManager);
        songAdapter.setData(getSongs());
        recyclerViewPlayItem.setAdapter(songAdapter);
    }

    private ArrayList<SongModel> getSongs() {
        ArrayList<SongModel> songs = new ArrayList<>();
        songs.add(new SongModel(0,"2","3","3","Hỉ",R.drawable.poster1,"Thập Đẳng Ma Quân",R.raw.hi));
        songs.add(new SongModel(1,"2","3","3","Sao mình chưa nắm tay nhau",R.drawable.poster2,"Hạ 2",R.raw.sao_minh_chua_nam_tay_nhau));
        songs.add(new SongModel(2,"2","3","3","Siêu cô đơn",R.drawable.poster3,"Yan Nguyễn",R.raw.sieu_co_don));
        songs.add(new SongModel(3,"2","3","3","Thập Đẳng Ma Quân",R.drawable.poster4,"Thập đẳng Ma Quân",R.raw.thap_dang_ma_quan));
        songs.add(new SongModel(4,"2","3","3","Thiên Nam Ca",R.drawable.poster4,"Thập Đẳng Ma Quân",R.raw.thien_nam_ca));
        return songs;
    }
}