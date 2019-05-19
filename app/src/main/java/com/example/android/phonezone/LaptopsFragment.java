package com.example.android.phonezone;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

public class LaptopsFragment extends Fragment {

   // TextView souqDocText;
    String souqUrl = "https://egypt.souq.com/eg-en/laptops/s/?as=1&section=2&page=1";
    List<Laptop> laptopList ;
    LaptopsAdapter adapter;
    RecyclerView recyclerView;
    List<String> imagesList;
    ProgressBar progressBar;


    class DownloadSouqContent extends AsyncTask<String , Void , String>

    {
        String resultText;

        @Override
        protected String doInBackground(String... strings) {


            try {
                Document souqDoc = Jsoup.connect(strings[0]).get();

                Elements price = souqDoc.select(".itemPrice:contains(EGP)");
                Elements name = souqDoc.select(".itemTitle");
                Elements specs = souqDoc.select(".selling-points");
               // Elements pic = souqDoc.select(".img-size-medium");


                //String link = pic.attr("src");

                laptopList = new ArrayList<>();
               // imagesList = new ArrayList();
                for (int i = 0; i < price.size(); i++) {
                    Element pic = souqDoc.select(".img-size-medium").get(i);
                    String imUrl = pic.attr("data-src");
                    laptopList.add(new Laptop(name.get(i).text(), specs.get(i).text(), price.get(i).text() ,imUrl));

                }
                return "done";

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new LaptopsAdapter( laptopList , getContext());
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.INVISIBLE);
            //Toast.makeText(getContext(), imagesList.get(0), Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // use jsoup web scraper to scrape souq web site
        DownloadSouqContent downloadSouqContent = new DownloadSouqContent();
        downloadSouqContent.execute(souqUrl);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.laptops_fragment, container , false);
      // souqDocText = v.findViewById(R.id.souqDocText);
       recyclerView = v.findViewById(R.id.recycler_view);
       progressBar = v.findViewById(R.id.progressBar2);
       progressBar.setVisibility(View.VISIBLE);

       return v;
    }
}
