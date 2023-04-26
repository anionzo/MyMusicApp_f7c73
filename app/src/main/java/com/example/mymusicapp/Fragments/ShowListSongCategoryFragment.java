// Hiển thị danh sách bài hát có trong category  khi được chọn
package com.example.mymusicapp.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.mymusicapp.Models.CategoryModel;
import com.example.mymusicapp.Models.SliderModel;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;


public class ShowListSongCategoryFragment extends Fragment {

    private TextView nameListPlay;
    private RecyclerView recyclerViewPlayItem;
    private ViewPager slider;
    private SliderAdapter sliderAdapter;
    private SongAdapter songAdapter;
    private ArrayList<SongModel> songModelArrayList = new ArrayList<>();
    //private TextView seeMorePlaylist;
    private ArrayList<SliderModel> sliderModel;
    LinearLayoutManager layoutManager;
    SQLiteDatabase database = null;
    String databaseNameWithPath = "/data/data/com.example.mymusicapp/databases/mymusicapp.db";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_category, container, false);

        nameListPlay = view.findViewById(R.id.name_list_play);
        recyclerViewPlayItem = view.findViewById(R.id.list_play_item);
        slider =view.findViewById(R.id.slider);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Lấy id từ bên list category ra
        Bundle bundle = getActivity().getIntent().getExtras();
        CategoryModel category = (CategoryModel) bundle.getSerializable("itemCategory");


        //
        sliderModel = new ArrayList<>();
        // Set IMG của 1
        if (category != null){

            sliderModel.add(new SliderModel(category.getImgCategory(),category.getNameCategory()));
            sliderAdapter = new SliderAdapter(getContext(),sliderModel);
            slider.setAdapter(sliderAdapter);

            // Gán dữ liệu trống
            songAdapter = new SongAdapter(getContext(),songModelArrayList);
            layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
            recyclerViewPlayItem.setLayoutManager(layoutManager);

            // Gán dữ liệu cho Song Adapter
            songAdapter.setData(getSongs(category.getIdCategory()));
            recyclerViewPlayItem.setAdapter(songAdapter);
        }
        else
            sliderModel.add(new SliderModel(getString(R.string.url_img1),"Không có âm nhạc!"));
    }

    private ArrayList<SongModel> getSongs(String idCategory) {
        ArrayList<SongModel> songs = new ArrayList<>();
        String selection = "idCaregory =?" ;
        String[] selectionArgs = {idCategory};

        database = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath,null);
        Cursor c = database.query("Song", null, selection, selectionArgs, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){

            while (c.moveToNext()) {
                String idSong = c.getString(0);
                String idCate = c.getString(1);
                String idAlbum = c.getString(2);
                String nameSong = c.getString(3);
                String linkImg = c.getString(4);
                String nameSinger = c.getString(5);
                String linkSong = c.getString(6);
                SongModel song = new SongModel(idSong, idCate, idAlbum,nameSong, linkImg,nameSinger,linkSong);
                songs.add(song);
            }
            c.close();
        }
        c.close();
        return songs;
    }
}