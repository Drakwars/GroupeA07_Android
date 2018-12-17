package com.example.flori.groupea07_mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
import com.example.flori.groupea07_mobile.Model.RetrofitInstance;
import com.example.flori.groupea07_mobile.Model.SellerUser;
import com.example.flori.groupea07_mobile.Service.GetDataService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddObjectActivity extends AppCompatActivity{
    private EditText mNameObjectView, mPriceObjectView, mDescriptionView, mCatView;
    private Button btnAddObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);

        mNameObjectView = (EditText) findViewById(R.id.et_log_nameobject);
        mPriceObjectView = (EditText) findViewById(R.id.et_log_priceobject);
        mDescriptionView = (EditText) findViewById(R.id.et_log_descriptionobject);
        mCatView = (EditText) findViewById(R.id.et_log_catobject);

        btnAddObject = (Button) findViewById(R.id.btn_addobject);
        btnAddObject.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences loginData = getSharedPreferences("memberInfo", Context.MODE_PRIVATE);
                final int idUser = loginData.getInt("idUser",0);




                GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                Call<List<SellerUser>> call = service.groupSellerUserList();
                call.enqueue(new Callback<List<SellerUser>>() {
                    @Override
                    public void onResponse(Call<List<SellerUser>> call, Response<List<SellerUser>> response) {
                        boolean selUser = false;
                        for(SellerUser m : response.body()){

                            if(m.getIdUser() == idUser){
                                GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                                SellerUser sell = new SellerUser(m.getIdSeller(), m.getUsername(), m.getNbSales()+1, m.getPositiveVote(), m.getNegativeVote(), m.getIdUser());
                                Call<ResponseBody> callUpdate = service.updateSale(Integer.toString(m.getIdSeller()),sell);
                                callUpdate.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {  }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {}
                                });
                                selUser = true;
                                break;
                            }
                        }
                        if(selUser == false){
                            SharedPreferences loginData = getSharedPreferences("memberInfo", Context.MODE_PRIVATE);
                            String logUsername = loginData.getString("userName",null);

                            GetDataService serviceSellUser = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                            Call<SellerUser> callSellUser = serviceSellUser.createSellerUser(new SellerUser(0, logUsername, 1, 0, 0,idUser));
                            callSellUser.enqueue(new Callback<SellerUser>() {
                                @Override
                                public void onResponse(Call<SellerUser> call, Response<SellerUser> response) {  }

                                @Override
                                public void onFailure(Call<SellerUser> call, Throwable t) { }
                            });
                        }

                    }
                    @Override
                    public void onFailure(Call<List<SellerUser>> call, Throwable t) {}
                });

                GetDataService serviceSold = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                Call<AuctionedObject> callSold = serviceSold.createAuctionedObject(new AuctionedObject(0, mNameObjectView.getText().toString(), mDescriptionView.getText().toString(), Integer.parseInt(mPriceObjectView.getText().toString()), idUser, mCatView.getText().toString()));

                callSold.enqueue(new Callback<AuctionedObject>() {
                    @Override
                    public void onResponse(Call<AuctionedObject> call, Response<AuctionedObject> response) { finish(); }

                    @Override
                    public void onFailure(Call<AuctionedObject> call, Throwable t) { }
                });
            }
        });
        // Toolbar
        Toolbar toolBar = (Toolbar) findViewById(R.id.add_object_toolbar);
        toolBar.setTitle(getResources().getText(R.string.txt_add_object));
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
