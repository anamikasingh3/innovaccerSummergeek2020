package com.innnovacer.summergeek2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Displayhost extends AppCompatActivity {
        RecyclerView host_list_view;
        private List<modelHostDisplay> host_list;

blogpostid blogpostid;
        private FirebaseFirestore firebaseFirestore;
        //private FirebaseAuth firebaseAuth;
        private adapterdisplayhostlist adapterdisplayhostlist;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_displayhost);

           // String valm=getIntent().getStringExtra("hin");
            getSupportActionBar().setTitle("Host List");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            host_list = new ArrayList<modelHostDisplay>();

            host_list_view = findViewById(R.id.rv);


            adapterdisplayhostlist = new adapterdisplayhostlist(host_list);

            firebaseFirestore = FirebaseFirestore.getInstance();




            host_list_view = (RecyclerView)findViewById(R.id.rv);
            host_list_view.setHasFixedSize(true);
            host_list_view.setLayoutManager(new LinearLayoutManager(this));

            //blogRecyclerAdapter = new adapterAddDoctors(blog_list);

            firebaseFirestore = FirebaseFirestore.getInstance();
            host_list_view.setLayoutManager(new LinearLayoutManager(Displayhost.this, RecyclerView.VERTICAL, false) {
                @Override
                public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {

                    // force height of viewHolder here, this will override layout_height from xml
                    //lp.width = getWidth() * 2 / 5;
                    return true;
                }
            });
            host_list_view.setAdapter(adapterdisplayhostlist);
            firebaseFirestore.collection("Host").addSnapshotListener(Displayhost.this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        String blogpostid = doc.getDocument().getId();
                        modelHostDisplay post = doc.getDocument().toObject(modelHostDisplay.class).withId(blogpostid);
                        host_list.add(post);

                        adapterdisplayhostlist.notifyDataSetChanged();

                    }

                }
            });



        }

    }


