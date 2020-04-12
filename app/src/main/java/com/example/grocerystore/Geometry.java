package com.example.grocerystore;

import com.google.gson.annotations.SerializedName;

public class Geometry {
    @SerializedName("location")
     private Location loc;

    public Geometry(Location loc) {
        this.loc = loc;
    }

    public Location getLoc() {
        return loc;
    }
}
