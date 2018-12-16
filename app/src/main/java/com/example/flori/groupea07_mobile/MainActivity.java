package com.example.flori.groupea07_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        Intent intent = new Intent(this, AuctionedObjectActivity.class);
        startActivity(intent); //Et puis là on switch
    }

}
