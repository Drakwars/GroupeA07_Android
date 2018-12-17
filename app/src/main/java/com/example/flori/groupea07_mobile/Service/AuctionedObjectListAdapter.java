package com.example.flori.groupea07_mobile.Service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.flori.groupea07_mobile.Model.AuctionedObject;
import com.example.flori.groupea07_mobile.Model.RetrofitInstance;
import com.example.flori.groupea07_mobile.Model.SoldObject;
import com.example.flori.groupea07_mobile.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.txtIdObject.setText(dataList.get(position).getIdObject()+"");
        holder.txtNameObject.setText(dataList.get(position).getNameObject());
        holder.txtPriceObject.setText(dataList.get(position).getPriceObject()+" €");
        holder.txtDescObject.setText("Description object : " + dataList.get(position).getDescriptionObject());
        holder.txtCatObject.setText("Category : " + dataList.get(position).getCatObject()+"");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {
        int positionMember, i;
        TextView txtIdObject, txtNameObject, txtPriceObject, txtCatObject, txtDescObject;
        Button btn_Buy;

        ObjectViewHolder(final View itemView) {
            super(itemView);
            txtIdObject =  itemView.findViewById(R.id.txt_id_object);
            txtIdObject.setVisibility(View.GONE);
            txtNameObject =  itemView.findViewById(R.id.txt_name_object);
            txtPriceObject =  itemView.findViewById(R.id.txt_price_object);
            txtDescObject = itemView.findViewById(R.id.txt_desc_object);
            txtCatObject = itemView.findViewById(R.id.txt_cat_object);

            btn_Buy = (Button) itemView.findViewById(R.id.bt_row_buy);
            btn_Buy.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    GetDataService serviceSold = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<SoldObject> callSold = serviceSold.createSoldObject(new SoldObject(Integer.parseInt(txtIdObject.getText().toString()), Integer.parseInt(txtPriceObject.getText().toString()), txtNameObject.getText().toString(), txtCatObject.getText().toString()));


                    callSold.enqueue(new Callback<SoldObject>() {
                        @Override
                        public void onResponse(Call<SoldObject> call, Response<SoldObject> response) {  }

                        @Override
                        public void onFailure(Call<SoldObject> call, Throwable t) { }
                    });
                    i = 0;
                    GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<List<AuctionedObject>> call = service.groupObjectList();
                    call.enqueue(new Callback<List<AuctionedObject>>() {
                        @Override
                        public void onResponse(Call<List<AuctionedObject>> call, Response<List<AuctionedObject>> response) {
                            for(AuctionedObject m : response.body()){
                                if(Integer.toString(m.getIdObject()).equals(txtIdObject.getText().toString())){
                                    setPositionMember(i);
                                    break;
                                }
                                i++;
                            }
                        }
                        @Override
                        public void onFailure(Call<List<AuctionedObject>> call, Throwable t) {}
                    });
                    GetDataService serviceDelete = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<ResponseBody> callDelete = serviceDelete.deleteObject(txtIdObject.getText().toString());

                    callDelete.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call1, Response<ResponseBody> response) {
                            dataList.remove(getPositionMember());
                            notifyItemRangeRemoved(getPositionMember(), 1);
                            notifyItemRemoved(getPositionMember());
                            notifyItemRangeChanged(getPositionMember(), dataList.size());
                            notifyDataSetChanged();
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {}
                    });
                }
            });
        }

        private void setPositionMember(int positionMember){
            this.positionMember = positionMember;
        }

        private int getPositionMember(){
            return positionMember;
        }
    }
}
