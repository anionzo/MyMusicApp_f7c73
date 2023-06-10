package com.example.mymusicapp.API.FragmetFire;

import static android.content.ContentValues.TAG;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymusicapp.API.Firebase.Database;
import com.example.mymusicapp.Adapters.SongAdapter;
import com.example.mymusicapp.Models.SingerModel;
import com.example.mymusicapp.Models.SongModel;
import com.example.mymusicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowListSongFragment extends Fragment {

    TextView text_name;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<SongModel> songs = new ArrayList<>();
    SongAdapter songAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_list_song, container, false);
        recyclerView = view.findViewById(R.id.list_song_frament);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        songAdapter = new SongAdapter(getContext(),songs);
        layoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        // Gán dữ liệu cho Song Adapter'
        songAdapter.cleanData();

        ArrayList<SingerModel> singerModels = new ArrayList<>();
        Database database = new Database();
        database.db.collection("singer")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SingerModel singerModel = new SingerModel(document.getId(),document.getString("nameSinger"),document.getString("imgSinger"));
                                singerModels.add(singerModel);
                            }
                            songAdapter.cleanData();
                            database.db.collection("music")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    String name = document.getString("idSinger");
                                                    for (SingerModel s : singerModels)
                                                    {
                                                        if(name.equals(s.getIdSinger())){
                                                            name = s.getNameSinger();
                                                        }
                                                    }
                                                    SongModel songModel = new SongModel(document.getId(), document.getString("idCategory"), document.getString("idAlbum"), document.getString("nameSong"), document.getString("linkImg"),name,document.getString("linkSong"));
                                                    songs.add(songModel);
                                                }
                                                songAdapter.setData(songs);
                                                recyclerView.setAdapter(songAdapter);
                                            } else {
                                                Log.d(TAG, "Error getting documents: ", task.getException());
                                            }
                                        }
                                    });
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }
}