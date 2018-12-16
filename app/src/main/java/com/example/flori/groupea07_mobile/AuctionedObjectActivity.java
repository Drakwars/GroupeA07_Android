package com.example.flori.groupea07_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
import com.example.flori.groupea07_mobile.Model.RetrofitInstance;
import com.example.flori.groupea07_mobile.Service.AuctionedObjectListAdapter;
import com.example.flori.groupea07_mobile.Service.GetMemberDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionedObjectActivity extends AppCompatActivity {

    private AuctionedObjectListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        /** Create handle for the RetrofitInstance interface*/
        GetMemberDataService service = RetrofitInstance.getRetrofitInstance().create(GetMemberDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<AuctionedObject>> call = service.groupObjectList();


        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<AuctionedObject>>() {
            @Override
            public void onResponse(Call<List<AuctionedObject>> call, Response<List<AuctionedObject>> response) {
                generateAuctionedObjectList(new ArrayList<>(response.body()));
                for(AuctionedObject a : response.body()){
                    Log.wtf("PARSER", a.toString());
                }
            }

            @Override
            public void onFailure(Call<List<AuctionedObject>> call, Throwable t) {
                Log.wtf("parser", t.getLocalizedMessage());
                Toast.makeText(AuctionedObjectActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateAuctionedObjectList(ArrayList<AuctionedObject> auctionedObjectArrayList  ) {
        recyclerView = findViewById(R.id.recycler_view_object_list);
        adapter = new AuctionedObjectListAdapter(auctionedObjectArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AuctionedObjectActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
