package com.kashey.conference.domain;

import com.google.firebase.Timestamp;

public class College {
    String collegename;
    String place;
    Timestamp tiimestamp;
    String img_url;

    public College() {

    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
