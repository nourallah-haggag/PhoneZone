package com.example.android.phonezone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PhoneBrandProductsActivity extends AppCompatActivity {

    String url ;
    List<PhoneModel> phonesList;
    RecyclerView recyclerView;
    PhoneBrandAdapter adapter;
    ProgressBar progressBar;

    class downloadGsmArenaData extends AsyncTask<String , Void , String>{

        String result;

        @Override
        protected String doInBackground(String... strings) {

            try {
                Document gsmDocument = Jsoup.connect(strings[0]).get();
                Element phoneNames = gsmDocument.select(".makers").first();
                Elements name = phoneNames.getElementsByTag("li");
                phonesList = new ArrayList<>();
                for(int i =0 ; i<name.size() ; i++)
                {
                    Element imgsSrc = name.get(i).getElementsByTag("img").first();
                    String title = name.get(i).text();
                    Log.i("title" , title);
                    String src = imgsSrc.attr("src");
                    /*URL url = new URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();

                    InputStream inputStream = connection.getInputStream();

                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                    Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);*/



                    phonesList.add(new PhoneModel(title , src));
                }

             //   result = name.text()+src;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return "done";
        }

        @Override
        protected void onPostExecute(String s) { // updating the ui after the thread has finished --> delegating to the main thread
            super.onPostExecute(s);
            //Toast.makeText(PhoneBrandProductsActivity.this, result, Toast.LENGTH_SHORT).show();
            recyclerView.setLayoutManager(new GridLayoutManager(PhoneBrandProductsActivity.this , 2));
            adapter = new PhoneBrandAdapter(getApplicationContext() ,phonesList);
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.INVISIBLE);
            //adapter.notifyDataSetChanged();



        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_brand_products);
        url = getIntent().getStringExtra("url");
        downloadGsmArenaData gsmArenaData = new downloadGsmArenaData();
        gsmArenaData.execute(url);

        recyclerView = findViewById(R.id.recycler_phone_brand);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);



    }
}
