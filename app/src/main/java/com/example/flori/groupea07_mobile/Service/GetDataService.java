package com.example.flori.groupea07_mobile.Service;

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
import com.example.flori.groupea07_mobile.Model.Member;
import com.example.flori.groupea07_mobile.Model.SellerUser;
import com.example.flori.groupea07_mobile.Model.SoldObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetDataService {


    @GET("api/member")
    Call<List<Member>> groupList();

    @GET("api/seller_user")
    Call<List<SellerUser>> groupSellerUserList();

    @GET("api/sold_object")
    Call<List<SoldObject>> groupObjectSoldList();

    @GET("api/member/{idUser}")
    Call<Member> getMemberById(@Path("idUser") int userId);

    @GET("api/auctioned_object")
    Call<List<AuctionedObject>> groupObjectList();

    @POST("api/seller_user")
    Call<SellerUser> createSellerUser(@Body SellerUser sellerUser);

    @POST("api/member")
    Call<Member> createUser(@Body Member user);

    @POST("api/sold_object")
    Call<SoldObject> createSoldObject(@Body SoldObject soldObject);

    @POST("api/auctioned_object")
    Call<AuctionedObject> createAuctionedObject(@Body AuctionedObject auctionedObject);

    @DELETE("api/member/{idUser}")
    Call<ResponseBody> deleteMember(@Path("idUser") String idUser);

    @DELETE("api/seller_user/{idUser}")
    Call<ResponseBody> deleteSellerUser(@Path("idUser") String idUser);

    @DELETE("api/auctioned_object/{idObject}")
    Call<ResponseBody> deleteObject(@Path("idObject") String idObject);

    @PUT("api/seller_user/{idUser}")
    Call<ResponseBody> updateSale(@Path("idUser") String idUser, @Body SellerUser sellerUser);

}