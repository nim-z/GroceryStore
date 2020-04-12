package com.example.grocerystore;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolder {
    String API_KEY = "AIzaSyCb4LFM1Z8CawASB9spTTv8k-PNplC8B7I";
    int radius = 20000;
    String type = "grocery_or_supermarket";
    String rank = "distance";
    @GET("/maps/api/place/nearbysearch/json?sensor=false&key=AIzaSyCb4LFM1Z8CawASB9spTTv8k-PNplC8B7I")
    public Call<Result> getStores(@Query("location") String location,@Query("radius") Integer rank,@Query("type") String type);
}
