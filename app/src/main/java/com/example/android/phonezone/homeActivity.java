package com.example.android.phonezone;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aafanasev.fonoapi.DeviceEntity;
import com.aafanasev.fonoapi.retrofit.FonoApiFactory;
import com.aafanasev.fonoapi.retrofit.FonoApiService;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class homeActivity extends AppCompatActivity  {

   // private TextView mTextMessage;
    private String baseUrl = "https://gsmarena-obrisiswox.now.sh/brands";
    String pexelsApi = "563492ad6f91700001000001269365517bc44208be56e6334b418cf6";
    String pexelApiUrl = "https://api.pexels.com/v1/search?query=samsung galaxy s8+query&per_page=15&page=1";



    // function for volley


  /* public void getImages()
   {
       StringRequest stringRequest = new StringRequest(Request.Method.GET, pexelApiUrl, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               Toast.makeText(homeActivity.this, response.toString(), Toast.LENGTH_LONG).show();
               Log.i("response" , response.toString());

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(homeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

           }
       }){
           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               Map<String , String> params = new HashMap<>();
               params.put("Authorization" , pexelsApi);
               return params;
           }
       };
       Volley.newRequestQueue(this).add(stringRequest);
   }*/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getFragmentManager()
                            .beginTransaction()
                            .replace( R.id.container ,new PhoneFragment() , "frag1")
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    getFragmentManager()
                            .beginTransaction()
                            .replace( R.id.container ,new LaptopsFragment() , "frag2")
                            .commit();
                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       // getImages();


       getFragmentManager()
               .beginTransaction()
               .add( R.id.container ,new PhoneFragment() , "frag1")
               .commit();





    }




}
