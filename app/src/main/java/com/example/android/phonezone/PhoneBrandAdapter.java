package com.example.android.phonezone;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhoneBrandAdapter extends RecyclerView.Adapter<PhoneBrandAdapter.PhonesHolder> {

    Context context;



    List<PhoneModel> phonesList;
    public PhoneBrandAdapter(Context context , List phonesList)
    {
        this.phonesList = phonesList;
        this.context = context;
    }
    @Override
    public PhonesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_phonebrand_row_item , parent , false);
        PhonesHolder phonesHolder = new PhonesHolder(v);
        return phonesHolder;
    }

    @Override
    public void onBindViewHolder(PhonesHolder holder, int position) {
        PhoneModel phoneModel = phonesList.get(position);
        holder.textView.setText(phoneModel.name);
       // holder.imageView.setImageBitmap(Bitmap.createScaledBitmap(phoneModel.imgUrl , 150 , 150 , false));
        Picasso.with(context).load(phoneModel.imgUrl).into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        return phonesList.size();
    }

    class PhonesHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public PhonesHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById( R.id.phone_image);
            textView = itemView.findViewById(R.id.device_name);
        }
    }
}
