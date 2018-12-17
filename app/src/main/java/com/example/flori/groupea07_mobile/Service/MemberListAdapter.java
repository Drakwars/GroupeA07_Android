package com.example.flori.groupea07_mobile.Service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.flori.groupea07_mobile.Model.Member;
import com.example.flori.groupea07_mobile.Model.RetrofitInstance;
import com.example.flori.groupea07_mobile.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {

    private ArrayList<Member> dataList;

    public MemberListAdapter(ArrayList<Member> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_member, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        holder.txtIdMember.setText(dataList.get(position).getIdUser()+"");
        holder.txtNameMember.setText(dataList.get(position).getUsername());
        holder.txtEmailMember.setText(dataList.get(position).getEmailUser());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder {
        private int positionMember, i;
        TextView txtIdMember, txtNameMember, txtEmailMember;
        Button btn_Delete;
        MemberViewHolder(final View itemView) {
            super(itemView);
            txtIdMember =  itemView.findViewById(R.id.txt_id_member);
            txtIdMember.setVisibility(View.GONE);
            txtNameMember =  itemView.findViewById(R.id.txt_name_member);
            txtEmailMember =  itemView.findViewById(R.id.txt_mail_member);

            btn_Delete = (Button) itemView.findViewById(R.id.bt_row_member_delete);
            btn_Delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    i = 0;
                    GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<List<Member>> call = service.groupList();
                    call.enqueue(new Callback<List<Member>>() {
                        @Override
                        public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                            for(Member m : response.body()){
                                if(Integer.toString(m.getIdUser()).equals(txtIdMember.getText().toString())){
                                    setPositionMember(i);
                                    break;
                                }
                                i++;
                            }
                        }
                        @Override
                        public void onFailure(Call<List<Member>> call, Throwable t) {}
                    });


                    GetDataService serviceDelete = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                    Call<ResponseBody> callDelete = serviceDelete.deleteMember(txtIdMember.getText().toString());
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
