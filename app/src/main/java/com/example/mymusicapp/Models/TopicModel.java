package com.example.mymusicapp.Models;

public class TopicModel {
    private String idTopic;
    private String nameTopic;
    private String imgTopic;

    public TopicModel(String idTopic, String nameTopic, String imgTopic) {
        this.idTopic = idTopic;
        this.nameTopic = nameTopic;
        this.imgTopic = imgTopic;
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getImgTopic() {
        return imgTopic;
    }

    public void setImgTopic(String imgTopic) {
        this.imgTopic = imgTopic;
    }
}
