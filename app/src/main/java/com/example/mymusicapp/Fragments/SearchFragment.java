package com.example.mymusicapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mymusicapp.Adapters.SongAdapter;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    private EditText searchSong;
    private RecyclerView recyclerViewSearchListSong;
    private LinearLayoutManager layoutManager;
    private SQLiteDatabase database = null;
    private  SongAdapter songAdapter ;
    private ArrayList<SongModel> songModelArrayList = new ArrayList<>();

    String databaseNameWithPath = "/data/data/com.example.mymusicapp/databases/mymusicapp.db";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchSong = view.findViewById(R.id.search_song);
        recyclerViewSearchListSong = view.findViewById(R.id.search_list_song);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        songAdapter = new SongAdapter(getContext(),songModelArrayList);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewSearchListSong.setLayoutManager(layoutManager);
        recyclerViewSearchListSong.setAdapter(songAdapter);

        if (!TextUtils.isEmpty(searchSong.getText().toString())){
            // Gán dữ liệu cho Song Adapter
            songAdapter.clearData();
            songAdapter.setData(getSongs(searchSong.getText().toString()));
            recyclerViewSearchListSong.setAdapter(songAdapter);
        }

    }
    private ArrayList<SongModel> getSongs(String name) {
        ArrayList<SongModel> songs = new ArrayList<>();
        String selection = "namSong LIKE ?";
        String[] selectionArgs = {"'%" + name + "%'"};

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