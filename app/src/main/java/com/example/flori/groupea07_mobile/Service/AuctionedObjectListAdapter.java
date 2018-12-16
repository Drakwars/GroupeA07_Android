package com.example.flori.groupea07_mobile.Service;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
import com.example.flori.groupea07_mobile.R;

import java.util.ArrayList;

public class AuctionedObjectListAdapter extends RecyclerView.Adapter<AuctionedObjectListAdapter.ObjectViewHolder> {

    private ArrayList<AuctionedObject> dataList;

    public AuctionedObjectListAdapter(ArrayList<AuctionedObject> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_object, parent, false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ObjectViewHolder holder, int position) {
        Log.wtf("parser", dataList.toString());
        holder.txtIdObject.setText(dataList.get(position).getIdObject()+"");
        holder.txtNameObject.setText(dataList.get(position).getNameObject());
        holder.txtEmailObject.setText(dataList.get(position).getPriceObject()+"");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {

        TextView txtIdObject, txtNameObject, txtEmailObject;

        ObjectViewHolder(View itemView) {
            super(itemView);
            txtIdObject =  itemView.findViewById(R.id.txt_id_object);
            txtNameObject =  itemView.findViewById(R.id.txt_name_object);
            txtEmailObject =  itemView.findViewById(R.id.txt_price_object);
        }
    }
}
