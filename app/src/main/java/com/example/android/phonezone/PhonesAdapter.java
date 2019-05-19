package com.example.android.phonezone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PhonesAdapter extends RecyclerView.Adapter<PhonesAdapter.PhonesHolder> {

    List<Phone> phonesList ;
    public PhonesAdapter(List<Phone> phonesList)
    {
        this.phonesList = phonesList;
    }
    @Override
    public PhonesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_recycler_item , parent , false);
        PhonesHolder phonesHolder = new PhonesHolder(v);
        return phonesHolder;
    }

    @Override
    public void onBindViewHolder(PhonesHolder holder, int position) {
            Phone phone = phonesList.get(position);
            holder.name.setText(phone.phoneName);
            holder.releaseDate.setText(phone.releasedAt);

    }


    @Override
    public int getItemCount() {
        return phonesList.size();
    }

    class PhonesHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView releaseDate;

        public PhonesHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.device_name);
            releaseDate = itemView.findViewById(R.id.released_at);

        }
    }
}
