package com.example.lyondrydelivery.Modal;

public class LocalResponse {
    String image;
    int uid;
    int itemid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public LocalResponse(String image, int uid) {
        this.image = image;
        this.uid = uid;

    }


    public LocalResponse(String image, int uid,int itemid) {
        this.image = image;
        this.uid = uid;
        this.itemid = itemid;
    }
}
