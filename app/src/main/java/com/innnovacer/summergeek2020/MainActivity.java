package com.innnovacer.summergeek2020;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
Button btnhost, btnvisitors,btndisplayhost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Innovaccer Summergeeks");
        FirebaseApp.initializeApp(this);
        btnhost=findViewById(R.id.btnhost);
        btnvisitors=findViewById(R.id.btnvisitor);
        btndisplayhost=findViewById(R.id.btnhostlist);
        btndisplayhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Displayhost.class);
                startActivity(intent);
            }
        });
        btnhost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Hostdetails.class);
                startActivity(intent);
            }
        });
        btnvisitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Visitorsdetails.class);
                startActivity(intent);
            }
        });
    }
}
