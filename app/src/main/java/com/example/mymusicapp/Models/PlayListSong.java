package com.example.mymusicapp.Models;

public class PlayListSong {
    private String idSong;
    private String idPlayList;

    public PlayListSong(String idSong, String idPlayList) {
        this.idSong = idSong;
        this.idPlayList = idPlayList;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }
}
