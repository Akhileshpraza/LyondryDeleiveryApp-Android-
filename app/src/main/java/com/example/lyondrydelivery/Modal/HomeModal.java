package com.example.lyondrydelivery.Modal;

public class HomeModal  {
    private String date;
    private String pickup_id;
    private String name;
    private String location;
    private String time;

    public HomeModal(String date, String pickup_id, String name, String location, String time) {
        this.date = date;
        this.pickup_id = pickup_id;
        this.name = name;
        this.location = location;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPickup_id() {
        return pickup_id;
    }

    public void setPickup_id(String pickup_id) {
        this.pickup_id = pickup_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
