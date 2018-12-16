package com.example.flori.groupea07_mobile.Service;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flori.groupea07_mobile.Model.Member;
import com.example.flori.groupea07_mobile.R;

import java.util.ArrayList;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {

    private ArrayList<Member> dataList;

    public MemberListAdapter(ArrayList<Member> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row_view, parent, false);
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

        TextView txtIdMember, txtNameMember, txtEmailMember;

        MemberViewHolder(View itemView) {
            super(itemView);
            txtIdMember =  itemView.findViewById(R.id.txt_id_member);
            txtNameMember =  itemView.findViewById(R.id.txt_name_member);
            txtEmailMember =  itemView.findViewById(R.id.txt_mail_member);
        }
    }
}
