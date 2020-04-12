package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private Call<Result> callStores;
    private String location;
    private GoogleMap googleMap;
    private double cenLat,cenLng;
    private Retrofit retrofit;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fetchPlaces();
    }
    public void fetchPlaces()
    {
        location = Objects.requireNonNull(getIntent().getExtras()).getString("location");
        retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        callStores = jsonPlaceHolder.getStores(location,JsonPlaceHolder.radius,JsonPlaceHolder.type);
        Places.initialize(getApplicationContext(),JsonPlaceHolder.API_KEY);
        PlacesClient placesClient = Places.createClient(this);
        callStores();

    }
    public void onMapReady(GoogleMap gMap) {
        this.googleMap=gMap;

        LatLng india = new LatLng(20.5937, 78.9629);
        Toast.makeText(Map.this,"Map is Ready",Toast.LENGTH_SHORT).show();
//        gMap.addMarker(new MarkerOptions().position(india)
//                .title("Marker in India"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india,4.0f));
    }

    public void addMarker(Double lat, Double lng, String text)
    {
        Log.d("Locations",text+lat+lng);
        LatLng location=new LatLng(lat.doubleValue(),lng.doubleValue());
        googleMap.addMarker(new MarkerOptions().position(location)
                .title(text));
        cenLat+=lat.doubleValue();
        cenLng+=lng.doubleValue();
    }
    void callStores()
    {
        callStores.enqueue(call);
    }
    Callback<Result> call = new Callback<Result>() {
        @Override
        public void onResponse(@NotNull Call<Result> call, @NotNull Response<Result> response) {
            Toast.makeText(Map.this, "Receiving response at " + location + " " + response.code(), Toast.LENGTH_SHORT).show();
            Result result = response.body();
            if(result.getStoreList().size()==0)
            {
                Toast.makeText(Map.this,"Empty Array",Toast.LENGTH_LONG).show();
            }
            int counter=1;
            for(Stores stores : response.body().getStoreList())
            {
                String title = stores.getTitle();
                Location loc=stores.getGeo().getLoc();
                count++;
                addMarker(Double.parseDouble(loc.getLatitude()),Double.parseDouble(loc.getLongitude()),title);
            }
            cenLat=cenLat/count;cenLng=cenLng/count;
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(cenLat,cenLng),11.0f));
        }

        @Override
        public void onFailure(Call<Result> call, Throwable t) {
            Toast.makeText(Map.this,"Error : |"+t.getMessage()+"|",Toast.LENGTH_SHORT).show();

        }
    };

}
