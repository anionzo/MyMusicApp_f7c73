package com.example.mymusicapp.Models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    // Thể loại
    private String idCategory;
    private String idTopic;
    private String nameCategory;
    private String imgCategory;

    public CategoryModel(String idCategory, String idTopic, String nameCategory, String imgCategory) {
        this.idCategory = idCategory;
        this.idTopic = idTopic;
        this.nameCategory = nameCategory;
        this.imgCategory = imgCategory;
    }

    public CategoryModel() {

    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImgCategory() {
        return imgCategory;
    }

    public void setImgCategory(String imgCategory) {
        this.imgCategory = imgCategory;
    }
    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

}
