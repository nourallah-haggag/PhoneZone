package com.example.android.phonezone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class latestPhoneListAdapter extends RecyclerView.Adapter<latestPhoneListAdapter.LatestPhoneHolder> {
    Context context;
    List<PhoneModel> latestList;
    public latestPhoneListAdapter(List<PhoneModel> latestList , Context context)
    {
        this.latestList = latestList;
        this.context = context;
    }
    @Override
    public LatestPhoneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_phones_layout , parent , false);
        LatestPhoneHolder holder = new LatestPhoneHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(LatestPhoneHolder holder, int position) {
        PhoneModel phoneModel = latestList.get(position);
        holder.textView.setText(phoneModel.name);
        Picasso.with(context).load(phoneModel.imgUrl).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return latestList.size();
    }

    public class LatestPhoneHolder extends RecyclerView.ViewHolder{

        TextView textView ;
        ImageView imageView;

        public LatestPhoneHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.latest_text);
            imageView = itemView.findViewById(R.id.latest_image);

        }
    }
}
