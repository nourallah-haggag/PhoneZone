package com.example.android.phonezone;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PhonesListActivity extends AppCompatActivity {

    List<Phone> phonesList;
    RecyclerView recyclerView;
    PhonesAdapter phonesAdapter;
    String pexelsApi;

    // function for populating the list
    public void getPhoneData(String brandName)
    {


        // populate the phones list
        String accessToken = "0edb2eaab19a7f6710e0a297f7a5b592433d30ad696aff2d";
        FonoApiService apiService = new FonoApiFactory().create();
        apiService.getLatest(accessToken , brandName ,50).enqueue(new Callback<List<DeviceEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<DeviceEntity>> call, retrofit2.Response<List<DeviceEntity>> response) {
                response.body().forEach(device->{
                    //Toast.makeText(homeActivity.this, device.getDeviceName(), Toast.LENGTH_SHORT).show();
                    phonesList.add(new Phone(device.getDeviceName() ,"Release date: "+ device.getAnnounced()));
                    //Toast.makeText(PhonesListActivity.this, device.getDeviceName(), Toast.LENGTH_SHORT).show();



                });
                phonesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DeviceEntity>> call, Throwable t) {

                Toast.makeText(PhonesListActivity.this, "please check your internet connection !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PhonesListActivity.this , homeActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phones_list);

        phonesList = new ArrayList<>();
        recyclerView =  findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));
        phonesAdapter = new PhonesAdapter(phonesList);
        recyclerView.setAdapter(phonesAdapter);
        getPhoneData(getIntent().getStringExtra("name"));






    }
}
