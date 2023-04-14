package com.example.mymusicapp.Models;

import java.io.Serializable;

public class SongModel implements Serializable{
    private int idSong;
    private String idPlayList;
    private String idCategory;
    private String idAlbum;
    private String nameSong;
    private int linkImg;
    private String nameSinger;
    private int linkSong;

    public SongModel(int idSong, String idPlayList, String idCategory, String idAlbum, String nameSong, int linkImg, String nameSinger, int linkSong) {
        this.idSong = idSong;
        this.idPlayList = idPlayList;
        this.idCategory = idCategory;
        this.idAlbum = idAlbum;
        this.nameSong = nameSong;
        this.linkSong = linkSong;
        this.nameSinger = nameSinger;
        this.linkImg = linkImg;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public int getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(int linkSong) {
        this.linkSong = linkSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public int getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(int linkImg) {
        this.linkImg = linkImg;
    }
}
