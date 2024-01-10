package com.example.eindopdrachtandroid.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Post implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int Id ;
    private String Type;
    private String Adres;
    private String District;

    private double longitude;

    private double latitude;

    public Post() {
    }

    @Ignore
    public Post(String type, String adres, String district, double longitude, double latitude) {
        Type = type;
        Adres = adres;
        District = district;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
