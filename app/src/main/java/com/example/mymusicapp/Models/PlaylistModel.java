package com.example.mymusicapp.Models;

import java.util.ArrayList;

public class PlaylistModel {
    private String idPlayList;
    private String namePlayList;
    private String imgPlayList;


    private ArrayList<SongModel> songLists;



    public PlaylistModel(String idPlayList, String namePlayList, String imgPlayList, ArrayList<SongModel> songLists) {
        this.idPlayList = idPlayList;
        this.namePlayList = namePlayList;
        this.imgPlayList = imgPlayList;
        this.songLists = songLists;
    }

    public ArrayList<SongModel> getSongLists() {
        return songLists;
    }

    public void setSongLists(ArrayList<SongModel> songLists) {
        this.songLists = songLists;
    }

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getNamePlayList() {
        return namePlayList;
    }

    public void setNamePlayList(String namePlayList) {
        this.namePlayList = namePlayList;
    }

    public String getImgPlayList() {
        return imgPlayList;
    }

    public void setImgPlayList(String imgPlayList) {
        this.imgPlayList = imgPlayList;
    }
}
