package com.innnovacer.summergeek2020;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Visitorsdetails extends AppCompatActivity {
TextInputEditText visitorsname,visitorsemail,visitorsph;
FirebaseFirestore firebaseFirestore;
Button visitorssubmit,visitorout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitorsdetails);
        getSupportActionBar().setTitle("Check-in now");
        String hostname=getIntent().getStringExtra("hname");
        String hostemail=getIntent().getStringExtra("hemail");
        String hostphone=getIntent().getStringExtra("hph");

        FirebaseApp.initializeApp(this);
        visitorout=findViewById(R.id.visitorout);
        visitorsname=findViewById(R.id.visitorsname);
        firebaseFirestore=FirebaseFirestore.getInstance();
        visitorsemail=findViewById(R.id.visitorsid);
      visitorsph=findViewById(R.id.visitorsph);
      visitorssubmit=findViewById(R.id.visitorssub);
        visitorssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String hname=visitorsname.getText().toString();
                final String hemail=visitorsemail.getText().toString();
               final String hph=visitorsph.getText().toString();
                if(TextUtils.isEmpty(hname)){
                    visitorsname.setError("This Item Cannot Be Left Empty");
                    return;
                }
                if(TextUtils.isEmpty(hemail)){
                    visitorsemail.setError("This Item Cannot Be Left Empty");
                    return;
                }
                if(TextUtils.isEmpty(hph)){
                    visitorsph.setError("This Item Cannot Be Left Empty");
                    return;
                }
                Map<String, Object> adminMap = new HashMap<>();

                adminMap.put("vname", hname);
                adminMap.put("vemail", hemail);
                adminMap.put("vph", hph);

                //final String currentuserid = firebaseAuth.getCurrentUser().getUid();
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("Visitor").add(adminMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Visitorsdetails.this,"Thank you for checkin",Toast.LENGTH_LONG).show();
                            sendemail(hname,hemail,hph);


            }}});}
        });

    }
    public void sendemail(final String vname, final String vemail, final String vph){
        Log.i("Send email", "");
        final String hostname=getIntent().getStringExtra("hname");
        final String hostemail=getIntent().getStringExtra("hemail");
        //Toast.makeText(this,hostemail,Toast.LENGTH_LONG).show();
       final String hostphone=getIntent().getStringExtra("hph");
        final String time= Calendar.getInstance().getTime().toString();

        String[] TO = {hostemail};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        //emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Someone visited");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "NAME: "+ vname +"\n"+ "Phone: " + vph +"\n"+ " Check-in Time "+ time );

            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            visitorout.setVisibility(View.VISIBLE);
            visitorssubmit.setVisibility(View.INVISIBLE);
visitorout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String time1= Calendar.getInstance().getTime().toString();

        String[] TO = {vemail};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
       // emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "You checked out");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "NAME: "+ vname+"\n" + "Phone: " + vph +"\n"+ " Check-in Time "+ time +"\n"+ "Check-Out time "+time1 +"\n"+ "HostName " +hostname+"\n" +" Address Visited: " + hostemail);

        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        visitorout.setVisibility(View.GONE);
        visitorssubmit.setVisibility(View.VISIBLE);
        finish();
    }
});
        //Toast.makeText(this,"two",Toast.LENGTH_LONG);

       // String time= Calendar.getInstance().getTime().toString();

        //intent.putExtra(Intent.EXTRA_TEXT, vname+ "email of visitor=" +vemail+" phone number of Visitor"+ vph+ "checkin time:"+time);


    }
}
