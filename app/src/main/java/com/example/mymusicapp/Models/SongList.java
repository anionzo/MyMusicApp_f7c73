package com.example.mymusicapp.Models;

import java.util.ArrayList;

public class SongList {

    private ArrayList<SongModel> songModels;

    public SongList(ArrayList<SongModel> songModels) {
        this.songModels = songModels;
    }

    public ArrayList<SongModel> getSongModels() {
        return songModels;
    }

    public void setSongModels(ArrayList<SongModel> songModels) {
        this.songModels = songModels;
    }
}
