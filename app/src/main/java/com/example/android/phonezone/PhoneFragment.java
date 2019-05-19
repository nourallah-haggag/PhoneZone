package com.example.android.phonezone;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Fade;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneFragment extends Fragment implements View.OnClickListener {
    CardView samView;
    CardView appView;
    CardView honorView;
    CardView huaweiView;
    CardView oppoView;
    CardView xiamView;
    RecyclerView recyclerView;
    Context context;
    List<PhoneModel> phonesList;
    latestPhoneListAdapter adapter;
    ProgressBar progressBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadGsmArenaData downloadGsmArenaData = new downloadGsmArenaData();
        downloadGsmArenaData.execute("https://www.gsmarena.com/");
    }

    class downloadGsmArenaData extends AsyncTask<String , Void , String> {

        String result;

        @Override
        protected String doInBackground(String... strings) {

            try {
                Document gsmDocument = Jsoup.connect(strings[0]).get();
                Elements phoneNames = gsmDocument.select(".module-phones-link");

                phonesList = new ArrayList<>();
                for(int i =0 ; i<phoneNames.size() ; i++)
                {
                    Element name = phoneNames.get(i);
                    Elements src = name.getElementsByTag("img");
                    String imgsSrc = src.attr("src");
                    String title = name.text();
                    result = title+imgsSrc;
                    Log.i("title" , title);





                    phonesList.add(new PhoneModel(title , imgsSrc));
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
           // Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.HORIZONTAL , false));
            adapter = new latestPhoneListAdapter(phonesList , getContext());
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.INVISIBLE);
            //adapter.notifyDataSetChanged();



        }
    }








    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.phone_brands_layout ,container , false);
        samView = v.findViewById(R.id.sam_card);
        samView.setOnClickListener(this);
        appView = v.findViewById(R.id.apple_card);
        appView.setOnClickListener(this);
        honorView = v.findViewById(R.id.honor_card);
        honorView.setOnClickListener(this);
        huaweiView = v.findViewById(R.id.huawei_card);
        huaweiView.setOnClickListener(this);
        oppoView = v.findViewById(R.id.oppo_card);
        oppoView.setOnClickListener(this);
        xiamView = v.findViewById(R.id.xiaomi_card);
        xiamView.setOnClickListener(this);
        progressBar = v.findViewById(R.id.progressBar5);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = v.findViewById(R.id.recycler_horizontal_latest);
        return v;



    }

    public void goToBrand(String url)
    {
        Intent intent = new Intent(getActivity() , PhoneBrandProductsActivity.class);
        intent.putExtra("url" , url);
        startActivity(intent);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.sam_card:
                goToBrand("https://www.gsmarena.com/samsung-phones-9.php");





                break;
            case R.id.apple_card:
                goToBrand("https://www.gsmarena.com/apple-phones-48.php");

                break;
            case R.id.honor_card:
                goToBrand("https://www.gsmarena.com/nokia-phones-1.php");

                break;
            case R.id.huawei_card:
                goToBrand("https://www.gsmarena.com/huawei-phones-58.php");

                break;
            case R.id.oppo_card:
                goToBrand("https://www.gsmarena.com/oppo-phones-82.php");

                break;
            case R.id.xiaomi_card:
                goToBrand("https://www.gsmarena.com/xiaomi-phones-80.php");

                break;


        }


    }
}
