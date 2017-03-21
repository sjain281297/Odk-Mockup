package com.example.shubham.odk_mockup;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
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

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }


    public void onItemRemove(final RecyclerView.ViewHolder viewHolder, final RecyclerView mRecyclerView) {

        final int adapterPosition = viewHolder.getAdapterPosition();
        final Form mForm = mData.get(adapterPosition);

        Snackbar snackbar = Snackbar
                .make(mRecyclerView, "Are you sure to delete?", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int mAdapterPosition = viewHolder.getAdapterPosition();
                        mData.add(mAdapterPosition, mForm);
                        notifyItemInserted(mAdapterPosition);
                        mRecyclerView.scrollToPosition(mAdapterPosition);
                        formToremove.remove(mForm);
                    }
                });
        snackbar.show();
        mData.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        formToremove.add(mForm);
    }



}
