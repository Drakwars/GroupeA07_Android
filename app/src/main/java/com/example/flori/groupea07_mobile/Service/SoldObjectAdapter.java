package com.example.flori.groupea07_mobile.Service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flori.groupea07_mobile.Model.SoldObject;
import com.example.flori.groupea07_mobile.R;

import java.util.ArrayList;

public class SoldObjectAdapter extends RecyclerView.Adapter<SoldObjectAdapter.ObjectViewHolder> {

    private ArrayList<SoldObject> dataList;

    public SoldObjectAdapter(ArrayList<SoldObject> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_soldobject, parent, false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ObjectViewHolder holder, int position) {
        holder.txtIdObject.setText(dataList.get(position).getIdObject()+"");
        holder.txtNameObject.setText(dataList.get(position).getNameObject());
        holder.txtPriceObject.setText(dataList.get(position).getFinalPrice()+" €");
        holder.txtCatObject.setText("Category object : " + dataList.get(position).getCatObject());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdObject, txtNameObject, txtPriceObject, txtCatObject;
        ObjectViewHolder(View itemView) {
            super(itemView);
            txtIdObject =  itemView.findViewById(R.id.txt_id_object);
            txtIdObject.setVisibility(View.GONE);
            txtNameObject =  itemView.findViewById(R.id.txt_name_object);
            txtPriceObject =  itemView.findViewById(R.id.txt_price_object);
            txtCatObject =  itemView.findViewById(R.id.txt_cat_object);
        }
    }
}
