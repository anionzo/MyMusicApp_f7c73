package com.example.mymusicapp.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymusicapp.Adapters.SongAdapter;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    private TextView txtnotification;
    private RecyclerView recyclerViewSearchListSong;
    private  SongAdapter songAdapter ;
    private LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    private ArrayList<SongModel> songModelArrayList = new ArrayList<>();
    private SQLiteDatabase database = null;
    String databaseNameWithPath = "/data/data/com.example.mymusicapp/databases/mymusicapp.db";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbar =(Toolbar) view.findViewById(R.id.toolbar_search_song);
        txtnotification = view.findViewById(R.id.notification);
        recyclerViewSearchListSong = view.findViewById(R.id.search_list_song);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu );
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("BBB", s);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        songAdapter = new SongAdapter(getContext(),songModelArrayList);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewSearchListSong.setLayoutManager(layoutManager);
        recyclerViewSearchListSong.setAdapter(songAdapter);

//        if (!TextUtils.isEmpty(searchSong.getText().toString())){
//            // Gán dữ liệu cho Song Adapter
//            songAdapter.clearData();
//            songAdapter.setData(getSongs(searchSong.getText().toString()));
//            recyclerViewSearchListSong.setAdapter(songAdapter);
//        }

    }

    private ArrayList<SongModel> getSongs(String name) {
        ArrayList<SongModel> songs = new ArrayList<>();
        database = SQLiteDatabase.openOrCreateDatabase(databaseNameWithPath,null);
        Cursor c = database.rawQuery("SELECT * FROM Song WHERE namSong LIKE '%" + name + "%'", null);
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
        }
        c.close();
        return songs;
    }
}