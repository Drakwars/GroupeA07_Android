package com.example.flori.groupea07_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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

        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<SoldObject>> call = service.groupObjectSoldList();

        call.enqueue(new Callback<List<SoldObject>>() {
            @Override
            public void onResponse(Call<List<SoldObject>> call, Response<List<SoldObject>> response) {
                generateSoldObjectList(new ArrayList<>(response.body()));
            }

            @Override
            public void onFailure(Call<List<SoldObject>> call, Throwable t) {
                Toast.makeText(SoldObjActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void generateSoldObjectList(ArrayList<SoldObject> soldObjectArrayList  ) {
        recyclerView = findViewById(R.id.recycler_view_object_list);
        adapter = new SoldObjectAdapter(soldObjectArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SoldObjActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
