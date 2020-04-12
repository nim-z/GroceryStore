package com.example.grocerystore;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("lat")
    private String latitude;

    @SerializedName("lng")
    private String longitude;

    public Location(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
