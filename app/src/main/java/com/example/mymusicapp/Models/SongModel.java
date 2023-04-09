package com.example.mymusicapp.Models;

public class SongModel {
    private String idSong;
    private String idPlayList;
    private String idCategory;
    private String idAlbum;
    private String nameSong;
    private String linkSong;
    private String idSinger;

    public SongModel(String idSong, String idPlayList, String idCategory, String idAlbum, String nameSong, String linkSong, String idAuthor) {
        this.idSong = idSong;
        this.idPlayList = idPlayList;
        this.idCategory = idCategory;
        this.idAlbum = idAlbum;
        this.nameSong = nameSong;
        this.linkSong = linkSong;
        this.idSinger = idAuthor;
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

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(String idSinger) {
        this.idSinger = idSinger;
    }
}
