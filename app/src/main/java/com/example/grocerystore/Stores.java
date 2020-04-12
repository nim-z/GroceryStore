package com.example.grocerystore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {
    @SerializedName("name")
    private String title;
    @SerializedName("vicinity")
    private String address;
    public Stores(String title, String address)
    {
        this.title = title;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }
}
