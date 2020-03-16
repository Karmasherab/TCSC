package com.kashey.conference.domain;


import com.google.firebase.Timestamp;

public class Members {
    String name;
    String designation;
    String age;
    Timestamp tiimestamp;
    String img_url;

    public Members() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Timestamp getTiimestamp() {
        return tiimestamp;
    }

    public void setTiimestamp(Timestamp tiimestamp) {
        this.tiimestamp = tiimestamp;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
