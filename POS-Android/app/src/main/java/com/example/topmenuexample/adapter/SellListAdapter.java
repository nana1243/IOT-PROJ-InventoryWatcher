package com.example.topmenuexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmenuexample.R;
import com.example.topmenuexample.frame.SalesVO;

import java.util.ArrayList;

public class SellListAdapter extends RecyclerView.Adapter<SellListAdapter.sViewHolder> {


    private final ArrayList<SalesVO> sdbList2;

    public class sViewHolder extends RecyclerView.ViewHolder{

        protected TextView txt_salesRegDate,txt_chainID,txt_totSales;

        public sViewHolder(View view){
            super(view);
            this.txt_salesRegDate = view.findViewById(R.id.txt_salesRegDate);
            this.txt_totSales = view.findViewById(R.id.txt_totSales);
            this.txt_chainID = view.findViewById(R.id.txt_chainID);
        }
    }

    public SellListAdapter(ArrayList<SalesVO> sdbList2){

        this.sdbList2 = sdbList2;


    }


    @NonNull
    @Override
    public sViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.daysell_list,viewGroup,false);
        sViewHolder viewHolder = new sViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull sViewHolder viewHolder, int position) {

        viewHolder.txt_totSales.setText(sdbList2.get(position).getRevenue()+"");
        viewHolder.txt_salesRegDate.setText(sdbList2.get(position).getDailySales()+"");
        viewHolder.txt_chainID.setText("chainID_1000000");

    }

    @Override
    public int getItemCount() {
        return (null != sdbList2 ? sdbList2.size() : 0);
    }





}
