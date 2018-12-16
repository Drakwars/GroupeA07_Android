package com.example.flori.groupea07_mobile.Service;

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
import com.example.flori.groupea07_mobile.Model.Member;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetDataService {


    @GET("api/member")
    Call<List<Member>> groupList();

    @GET("api/member/{id}")
    Call<Member> getMemberById(@Path("id") int userId);

    @GET("api/auctioned_object")
    Call<List<AuctionedObject>> groupObjectList();

    @POST("api/member")
    Call<Member> createUser(@Body Member user);
}
