package com.example.mymusicapp.Models;

import java.util.ArrayList;

public class PlaylistModel {
    private String idPlayList;
    private String namePlayList;
    private String imgPlayList;

    public PlaylistModel(String idPlayList, String namePlayList, String imgPlayList) {
        this.idPlayList = idPlayList;
        this.namePlayList = namePlayList;
        this.imgPlayList = imgPlayList;
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
