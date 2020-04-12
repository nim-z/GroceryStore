package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CardView delhi;
    private TextView delhi_text;
    private TextView mumbai_text;
    private TextView bangalore_text;
    private TextView chennai_text;
    private CardView mumbai;
    private CardView bangalore;
    private CardView chennai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        delhi = findViewById(R.id.delhi);
        mumbai = findViewById(R.id.mumbai);
        bangalore = findViewById(R.id.bangalore);
        chennai = findViewById(R.id.chennai);
        delhi_text = findViewById(R.id.delhi_text);
        mumbai_text = findViewById(R.id.mumbai_text);
        bangalore_text = findViewById(R.id.bangalore_text);
        chennai_text = findViewById(R.id.chennai_text);
        delhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = "28.704059,77.1025";
                Intent intent = new Intent(MainActivity.this,Display.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });
//        delhi_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String location = "28.7041,77.1025";
//                Intent intent = new Intent(MainActivity.this,Display.class);
//                intent.putExtra("location",location);
//                startActivity(intent);
//            }
//        });
        mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = "19.0760,72.8777";
                Intent intent = new Intent(MainActivity.this,Display.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });
//        mumbai_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String location = "19.0760,72.8777";
//                Intent intent = new Intent(MainActivity.this,Display.class);
//                intent.putExtra("location",location);
//                startActivity(intent);
//            }
//        });
        bangalore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = "12.9716,77.5946";
                Intent intent = new Intent(MainActivity.this,Display.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });
//        bangalore_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String location = "12.9716,77.5946";
//                Intent intent = new Intent(MainActivity.this,Display.class);
//                intent.putExtra("location",location);
//                startActivity(intent);
//            }
//        });
        chennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = "13.031744,80.181672";
                Intent intent = new Intent(MainActivity.this,Display.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });
//        chennai_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String location = "13.0827,80.2707";
//                Intent intent = new Intent(MainActivity.this,Display.class);
//                intent.putExtra("location",location);
//                startActivity(intent);
//            }
//        });
    }
}
