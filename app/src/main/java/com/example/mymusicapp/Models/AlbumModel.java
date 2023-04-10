package com.example.mymusicapp.Models;

import java.util.ArrayList;

public class AlbumModel {
    private String idAlbum;
    private String nameAlbum;
    private String imgAlbum;
    private ArrayList<SongModel> songs;

    public AlbumModel(String idAlbum, String nameAlbum, String imgAlbum, ArrayList<SongModel> songs) {
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
        this.imgAlbum = imgAlbum;
        this.songs = songs;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getImgAlbum() {
        return imgAlbum;
    }

    public void setImgAlbum(String imgAlbum) {
        this.imgAlbum = imgAlbum;
    }

    public ArrayList<SongModel> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<SongModel> songs) {
        this.songs = songs;
    }
}
