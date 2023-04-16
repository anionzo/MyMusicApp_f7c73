package com.example.mymusicapp.Models;

public class SliderModel {
    private String Img;
    private String slideName;

    public SliderModel(String img, String slideName) {
        this.Img = img;
        this.slideName = slideName;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }
}
