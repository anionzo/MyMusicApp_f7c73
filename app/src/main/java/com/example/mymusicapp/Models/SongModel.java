package com.example.mymusicapp.Models;

import java.io.Serializable;

public class SongModel implements Serializable{
    private int idSong;
    private String idCategory;
    private String idAlbum;
    private String nameSong;
    private String linkImg;
    private String nameSinger;
    private int linkSong;
    public SongModel(int idSong,String idCategory, String idAlbum, String nameSong, String linkImg, String nameSinger, int linkSong) {
        this.idSong = idSong;
        this.idCategory = idCategory;
        this.idAlbum = idAlbum;
        this.nameSong = nameSong;
        this.linkImg = linkImg;
        this.nameSinger = nameSinger;
        this.linkSong = linkSong;
    }



    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
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

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
