package com.example.android.phonezone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView signUpTxt;

    // function for login
    public void loginFunction(View view)
    {
        Intent intent = new Intent(this , homeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpTxt = findViewById(R.id.signUpTxt);
        signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SignupActivity.class);
                startActivity(intent);
            }
        });


    }
}
