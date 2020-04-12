package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.StyleSpan;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Display extends AppCompatActivity {
    private String location;
    private TextView text;
    private Boolean ok;
    private Boolean res;
    private Call<Result> callStores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        location = Objects.requireNonNull(getIntent().getExtras()).getString("location");
        text = findViewById(R.id.textView2);
        ok = false;
        text.setText("");
        text.setMovementMethod(new ScrollingMovementMethod());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        callStores = jsonPlaceHolder.getStores(location,JsonPlaceHolder.radius,JsonPlaceHolder.type);
        Places.initialize(getApplicationContext(),JsonPlaceHolder.API_KEY);
        PlacesClient placesClient = Places.createClient(this);
        callStores();
    }
    void callStores()
    {
        callStores.enqueue(call);
    }
    Callback<Result> call = new Callback<Result>() {
        @Override
        public void onResponse(@NotNull Call<Result> call, @NotNull Response<Result> response) {
            Toast.makeText(Display.this, "Receiving response at " + location + " " + response.code(), Toast.LENGTH_SHORT).show();
            Result result = response.body();
            if(result.getStoreList().size()==0)
            {
                Toast.makeText(Display.this,"Empty Array",Toast.LENGTH_LONG).show();
                text.append(response.body().getStatus());
            }
            int counter=1;
            for(Stores stores : response.body().getStoreList())
            {
                String title = stores.getTitle();
                String address = stores.getAddress();
                String final_string = counter + ". " + title + "\n" + address+ "\n\n\n";
                Spannable sb = new SpannableString(final_string);
                sb.setSpan(new StyleSpan(Typeface.BOLD),final_string.indexOf(title),final_string.indexOf(title)+title.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text.append(sb);
                counter++;
            }
        }

        @Override
        public void onFailure(Call<Result> call, Throwable t) {
            text.setText(t.getMessage());
        }
    };
}
