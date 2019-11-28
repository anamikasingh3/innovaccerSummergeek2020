package com.innnovacer.summergeek2020;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Hostdetails extends AppCompatActivity {
TextInputEditText hostname,hostemail,hostph;
FirebaseFirestore firebaseFirestore;
Button hostsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        getSupportActionBar().setTitle("Share Host Details");
        setContentView(R.layout.activity_hostdetails);
hostname=findViewById(R.id.hostname);
        hostemail=findViewById(R.id.hostid);
        hostph=findViewById(R.id.hostph);
        hostsubmit=findViewById(R.id.hostsub);
        hostsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hname=hostname.getText().toString();
                String hemail=hostemail.getText().toString();
                String hph=hostph.getText().toString();
                if(TextUtils.isEmpty(hname)){
                    hostname.setError("This Item Cannot Be Left Empty");
                    return;
                }
                if(TextUtils.isEmpty(hemail)){
                    hostemail.setError("This Item Cannot Be Left Empty");
                    return;
                }
                if(TextUtils.isEmpty(hph)){
                    hostph.setError("This Item Cannot Be Left Empty");
                    return;
                }
                Map<String, Object> adminMap = new HashMap<>();

                adminMap.put("hname", hname);
                adminMap.put("hemail", hemail);
                adminMap.put("hph", hph);

                //final String currentuserid = firebaseAuth.getCurrentUser().getUid();
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("Host").add(adminMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Hostdetails.this,"Registered as host",Toast.LENGTH_LONG).show();

                        }}});}
        });

            }


    }
