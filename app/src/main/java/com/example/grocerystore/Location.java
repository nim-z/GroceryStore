package com.example.grocerystore;

import com.google.gson.annotations.SerializedName;

public class Location {
    private String address;
    @SerializedName("lat")
    private String latitude;

    @SerializedName("lng")
    private String longitude;

    public Location(String latitude, String longitude) {
        address=null;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
}
