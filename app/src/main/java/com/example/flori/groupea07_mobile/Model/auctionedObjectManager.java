package com.example.flori.groupea07_mobile.Model;

import java.util.List;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.flori.groupea07_mobile.DAO.GenericDAO;

public class AuctionedObjectManager implements Callback{
    private List<Auctioned_object> auctioned_objectList;
    private static AuctionedObjectManager instance;
    private Callback callback;

    private AuctionedObjectManager() {}

    public static AuctionedObjectManager getInstance()
    {
        if(instance == null)
        {
            instance = new AuctionedObjectManager();
        }

        return instance;
    }

    public void loadMerchs(Callback callback)
    {
        this.callback = callback;

        GenericDAO dao = new GenericDAO(instance);
        dao.execute("auctioned_object",HttpRequestType.GET.toString(),"");
    }

    @Override
    public void onPreExecute(String text)
    {
        callback.onPreExecute("auctioned_object");
    }

    @Override
    public void onPostExecute(String result)
    {
        Log.e("auctioned_object",result);
        auctioned_objectList = new Gson().fromJson(result, new TypeToken<List<Auctioned_object>>(){}.getType());
        this.callback.onManagerLoaded("auctioned_object");
    }

    @Override
    public void onManagerLoaded(String manager) {

    }

    public List<Auctioned_object> getAuctioned_objectList()
    {
        return auctioned_objectList;
    }

    public Auctioned_object getAuctioned_objectListId(int id)
    {
        for(Auctioned_object obj : auctioned_objectList)
        {
            if(obj.getIdObject() == id)
            {
                return obj;
            }
        }

        return null;
    }
}

