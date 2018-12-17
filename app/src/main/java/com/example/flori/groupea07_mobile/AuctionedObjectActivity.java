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
import com.example.flori.groupea07_mobile.Service.GetDataService;

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
        setContentView(R.layout.card_layout_object_and_member);

        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<AuctionedObject>> call = service.groupObjectList();

        call.enqueue(new Callback<List<AuctionedObject>>() {
            @Override
            public void onResponse(Call<List<AuctionedObject>> call, Response<List<AuctionedObject>> response) {
                generateAuctionedObjectList(new ArrayList<>(response.body()));
            }

            @Override
            public void onFailure(Call<List<AuctionedObject>> call, Throwable t) {
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
