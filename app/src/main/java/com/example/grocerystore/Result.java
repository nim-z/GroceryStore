package com.example.grocerystore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {
    @SerializedName("results")
    private ArrayList<Stores> storeList;
    @SerializedName("status")
    String status;
    public Result(ArrayList<Stores> storeList,String status)
    {
        this.storeList = storeList;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Stores> getStoreList() {
        return storeList;
    }
}
