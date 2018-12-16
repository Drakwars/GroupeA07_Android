package com.example.flori.groupea07_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.flori.groupea07_mobile.Model.Member;
import com.example.flori.groupea07_mobile.Model.RetrofitInstance;
import com.example.flori.groupea07_mobile.Service.GetDataService;
import com.example.flori.groupea07_mobile.Service.MemberListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberActivity extends AppCompatActivity {

    private MemberListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_layout_object_and_member);

        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<Member>> call = service.groupList();


        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                generateMemberList(new ArrayList<>(response.body()));
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {
                Log.wtf("parser", t.getLocalizedMessage());
                Toast.makeText(MemberActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateMemberList(ArrayList<Member> memberArrayList  ) {
        recyclerView = findViewById(R.id.recycler_view_object_list);
        adapter = new MemberListAdapter(memberArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MemberActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
