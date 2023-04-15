package com.example.mymusicapp.Models;

public class SingerModel {
    private String idSinger;
    private String nameSinger;
    private String imgSinger;

    public SingerModel(String idAuthor, String nameAuthor, String imgAuthor) {
        this.idSinger = idAuthor;
        this.nameSinger = nameAuthor;
        this.imgSinger = imgAuthor;
    }

    public String getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(String idSinger) {
        this.idSinger = idSinger;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getImgSinger() {
        return imgSinger;
    }

    public void setImgSinger(String imgSinger) {
        this.imgSinger = imgSinger;
    }


}
