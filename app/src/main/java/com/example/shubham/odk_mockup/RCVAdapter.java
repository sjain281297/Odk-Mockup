package com.example.shubham.odk_mockup;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shubham on 21/3/17.
 */

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.MyHolder> {

    Context mContext;
    ArrayList<Form> mData=new ArrayList<>();
    ArrayList<Form> formToremove;
    public ArrayList<Form> selectedList=new ArrayList<>();


    public RCVAdapter(Context mContext, ArrayList<Form> mData,ArrayList<Form> selectedList) {
        this.mContext = mContext;
        this.mData = mData;
        this.selectedList=selectedList;
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

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
// generate random color
        int color1 = generator.getRandomColor();
        TextDrawable drawable2 = null;
        switch (position){
            case 0:
                drawable2 = TextDrawable.builder()
                        .buildRound("B", color1);
                break;
            case 1:
                drawable2 = TextDrawable.builder()
                        .buildRound("C", color1);
                break;
            case 2:
                drawable2 = TextDrawable.builder()
                        .buildRound("B", color1);
                break;
            case 3:
                drawable2 = TextDrawable.builder()
                        .buildRound("B", color1);
                break;

        }

        holder.img.setImageDrawable(drawable2);


        if(selectedList==null){

        }else
        if(selectedList.contains(mData.get(position))){
            holder.cardView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.darkGrey));
//            holder.tick.setVisibility(View.VISIBLE);
            holder.img.setImageResource(R.drawable.tick);
        }
        else {
            holder.cardView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.cardview_light_background));
            holder.tick.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView formName;
        TextView formStatus;
        TextView timestamp;
        CardView cardView;
        ImageView tick;
        ImageView img;

        public MyHolder(View itemView) {
            super(itemView);
            formName=(TextView) itemView.findViewById(R.id.formName);
            formStatus=(TextView)itemView.findViewById(R.id.formStatus);
            timestamp=(TextView)itemView.findViewById(R.id.timestamp);
            cardView=(CardView)itemView.findViewById(R.id.card);
            tick=(ImageView)itemView.findViewById(R.id.tick);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
    }









}
