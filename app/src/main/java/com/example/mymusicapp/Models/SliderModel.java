package com.example.mymusicapp.Models;

public class SliderModel {
    private int Img;
    private String slideName;

    public SliderModel(int img, String slideName) {
        Img = img;
        this.slideName = slideName;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }
}
