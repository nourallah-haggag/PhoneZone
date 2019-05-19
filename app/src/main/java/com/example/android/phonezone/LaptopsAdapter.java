package com.example.android.phonezone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LaptopsAdapter extends RecyclerView.Adapter<LaptopsAdapter.LaptopHolder> {


    private Context context;

    List<Laptop> laptopsList;
    public LaptopsAdapter(List laptopsList , Context context)
    {
        this.laptopsList = laptopsList;
        this.context = context;
    }
    @Override
    public LaptopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.laptop_recycler_item , parent , false);
        LaptopHolder holder = new LaptopHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(LaptopHolder holder, int position) {
        Laptop laptop = laptopsList.get(position);
        holder.name.setText(laptop.name);
        holder.specs.setText(laptop.specs);
        holder.price.setText(laptop.price);
        Picasso.with(context).load(laptop.url).into(holder.image);



    }



    @Override
    public int getItemCount() {
        return laptopsList.size();
    }

    class LaptopHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView specs;
        TextView price;
        ImageView image;


        public LaptopHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            specs = itemView.findViewById(R.id.specs);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.lapImage);
        }
    }
}
