package com.example.flori.groupea07_mobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.flori.groupea07_mobile.Model.AuctionedObjectManager;
import com.example.flori.groupea07_mobile.Model.Auctioned_object;

import java.util.List;

public class ObjectActivity extends AppCompatActivity {
    protected AuctionedObjectManager test;
    List<Auctioned_object> listObject;
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        Log.d("CREATION", AuctionedObjectManager.getInstance().getAuctioned_objectListId(0).toString());
        Log.d("CREATION", "TEST");
        AdapaterObject adbObject;
        listObject = AuctionedObjectManager.getInstance().getAuctioned_objectList();

        adbObject = new AdapaterObject(this, R.layout.layout_object_list);
        ListView listView = (ListView) findViewById(R.id.lv_Object);
        listView.setAdapter(adbObject);
    }

}
