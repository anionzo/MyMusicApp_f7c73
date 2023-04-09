package com.example.mymusicapp.Models;

public class AlbumModel {
    private String idAlbum;
    private String nameAlbum;
    private String imgAlbum;
    private SongList songs;

    public AlbumModel(String idAlbum, String nameAlbum, String imgAlbum, SongList songs) {
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

    public SongList getSongs() {
        return songs;
    }

    public void setSongs(SongList songs) {
        this.songs = songs;
    }
}
