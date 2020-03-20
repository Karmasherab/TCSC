package com.kashey.conference.domain;

import com.google.firebase.Timestamp;

public class Post {
    String img_url;
    String description;
    Timestamp tiimestamp;

    public Timestamp getTiimestamp() {
        return tiimestamp;
    }

    public void setTiimestamp(Timestamp tiimestamp) {
        this.tiimestamp = tiimestamp;
    }

    public Post() {
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
