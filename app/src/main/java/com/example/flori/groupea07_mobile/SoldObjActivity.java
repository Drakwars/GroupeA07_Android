package com.example.flori.groupea07_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
import com.example.flori.groupea07_mobile.Model.RetrofitInstance;
import com.example.flori.groupea07_mobile.Model.SoldObject;
import com.example.flori.groupea07_mobile.Service.GetDataService;
import com.example.flori.groupea07_mobile.Service.SoldObjectAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoldObjActivity extends AppCompatActivity {
    private SoldObjectAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_layout_object_and_member);


        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<SoldObject>> call = service.groupObjectSoldList();


        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<SoldObject>>() {
            @Override
            public void onResponse(Call<List<SoldObject>> call, Response<List<SoldObject>> response) {
                generateSoldObjectList(new ArrayList<>(response.body()));
            }

            @Override
            public void onFailure(Call<List<SoldObject>> call, Throwable t) {
                Log.wtf("parser", t.getLocalizedMessage());
                Toast.makeText(SoldObjActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateSoldObjectList(ArrayList<SoldObject> soldObjectArrayList  ) {
        recyclerView = findViewById(R.id.recycler_view_object_list);
        adapter = new SoldObjectAdapter(soldObjectArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SoldObjActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

}
