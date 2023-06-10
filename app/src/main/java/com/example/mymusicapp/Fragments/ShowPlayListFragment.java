package com.example.mymusicapp.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusicapp.Adapters.SongAdapter;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;

public class ShowPlayListFragment extends Fragment {

    TextView text_name;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<SongModel> songs = new ArrayList<>();
    SongAdapter songAdapter;
    SQLiteDatabase database = null;
    String databaseNameWithPath = "/data/data/com.example.mymusicapp/databases/mymusicapp.db";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_show_play_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_song);
        text_name = view.findViewById(R.id.txt_name_play_list);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Gán dữ liệu trống
        songAdapter = new SongAdapter(getContext(),songs);
        layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        // Gán dữ liệu cho Song Adapter'
        songAdapter.cleanData();
        songAdapter.setData(getSongs());
        recyclerView.setAdapter(songAdapter);
    }

    private ArrayList<SongModel> getSongs() {
        ArrayList<SongModel> songs = new ArrayList<>();
        database = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath,null);
        Cursor c = database.query("Song", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
             String idSong = c.getString(0);
                String idCate = c.getString(1);
                String idAlbum = c.getString(2);
                String nameSong = c.getString(3);
                String linkImg = c.getString(4);
                String nameSinger = c.getString(5);
                String linkSong = c.getString(6);
                SongModel song = new SongModel(idSong, idCate, idAlbum,nameSong, linkImg,nameSinger,linkSong);
                songs.add(song);
                c.moveToNext();
        }
        c.close();
        return songs;
    }
}