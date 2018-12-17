package com.example.flori.groupea07_mobile.Service;

    /*@Delete
    public void deleteUsers(User... users);*/

    /*@FormUrlEncoded
    @POST("notice/edit")
    Call<Notice> updateNotice(@Field("id") String id, @Field("title") String title);*/

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
        import com.example.flori.groupea07_mobile.Model.Member;
import com.example.flori.groupea07_mobile.Model.SoldObject;

import java.util.List;

        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.http.Body;
        import retrofit2.http.DELETE;
        import retrofit2.http.Field;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.Path;

public interface GetDataService {


    @GET("api/member")
    Call<List<Member>> groupList();

    @GET("api/sold_object")
    Call<List<SoldObject>> groupObjectSoldList();

    @GET("api/member/{idUser}")
    Call<Member> getMemberById(@Path("idUser") int userId);

    @GET("api/auctioned_object")
    Call<List<AuctionedObject>> groupObjectList();

    @POST("api/member")
    Call<Member> createUser(@Body Member user);

    @POST("api/sold_object")
    Call<SoldObject> createSoldObject(@Body SoldObject soldObject);

    @DELETE("api/member/{idUser}")
    Call<ResponseBody> deleteMember(@Path("idUser") String idUser);

    @DELETE("api/auctioned_object/{idObject}")
    Call<ResponseBody> deleteObject(@Path("idObject") String idObject);
}