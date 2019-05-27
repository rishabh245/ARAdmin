package com.example.rishabh.aradmin.activities.manage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rishabh.aradmin.R;
import com.example.rishabh.aradmin.model.AdDetails;
import com.example.rishabh.aradmin.model.ProfileAd;

import java.util.ArrayList;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.AdsViewHolder> {
    Context context;
    ArrayList<ProfileAd> mArrayList;
    AdClicked mListener;

    public ManageAdapter(Context context, AdClicked mListener, ArrayList<ProfileAd> arrayList){
        this.context=context;
        this.mArrayList=arrayList;
        this.mListener = mListener;
    }

    @Override

    public AdsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.one_ad_view,parent,false);
        return new AdsViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(AdsViewHolder holder, int position) {
        holder.nameTextView.setText(mArrayList.get(position).getName());
        holder.addressTextView.setText(mArrayList.get(position).getAddress());
        holder.priceTextView.setText("Rs " + mArrayList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }


    class AdsViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView addressTextView;
        TextView priceTextView;
        ImageView deleteImageView;
        AdClicked mListener;

        AdsViewHolder(View itemView, final AdClicked mListener) {
            super(itemView);
            nameTextView=itemView.findViewById(R.id.tv_name);
            addressTextView=itemView.findViewById(R.id.tv_address);
            priceTextView = itemView.findViewById(R.id.tv_price);
            deleteImageView=itemView.findViewById(R.id.delete_icon);
           // itemView.setOnClickListener(this);
            this.mListener=mListener;
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    mListener.onDeleteButtonClicked(view,pos);
                }
            });


        }


    }




    public interface AdClicked  {
        void onDeleteButtonClicked(View view, int position);
    }

}
