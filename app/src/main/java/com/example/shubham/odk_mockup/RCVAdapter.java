package com.example.shubham.odk_mockup;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shubham on 21/3/17.
 */

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.MyHolder> {

    Context mContext;
    ArrayList<Form> mData;
    ArrayList<Form> formToremove;


    public RCVAdapter(Context mContext, ArrayList<Form> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.formToremove = new ArrayList<>();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.form_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.formName.setText(mData.get(position).getFormName());
        if(mData.get(position).getFormStatus().equals("Finalized")){
            holder.formStatus.setTextColor(ContextCompat.getColor(mContext,R.color.green));
            holder.formStatus.setBackground(ContextCompat.getDrawable(mContext,R.drawable.back_final));
        }else if(mData.get(position).getFormStatus().equals("Sent")){
            holder.formStatus.setTextColor(ContextCompat.getColor(mContext,R.color.orange));
            holder.formStatus.setBackground(ContextCompat.getDrawable(mContext,R.drawable.back_sent));
        }

        holder.formStatus.setText(mData.get(position).getFormStatus());
        holder.timestamp.setText(mData.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView formName;
        TextView formStatus;
        TextView timestamp;

        public MyHolder(View itemView) {
            super(itemView);
            formName=(TextView) itemView.findViewById(R.id.formName);
            formStatus=(TextView)itemView.findViewById(R.id.formStatus);
            timestamp=(TextView)itemView.findViewById(R.id.timestamp);
        }
    }




}
