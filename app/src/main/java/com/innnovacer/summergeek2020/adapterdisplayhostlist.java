package com.innnovacer.summergeek2020;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class adapterdisplayhostlist extends RecyclerView.Adapter<adapterdisplayhostlist.viewHolder> {
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public Context context;
    public List<modelHostDisplay> blog_list;

    //private FirebaseDatabase mdatabase;
    public adapterdisplayhostlist(List<modelHostDisplay> blog_list) {

        this.blog_list = blog_list;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlehost, parent, false);
        context = parent.getContext();

        firebaseFirestore = FirebaseFirestore.getInstance();

        return new viewHolder(view);


    }



    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, final int Position) {
        viewHolder.setIsRecyclable(false);
        final String blogpostid = blog_list.get(Position).blogpostid;
        final String email = blog_list.get(Position).getHemail();
        final String name = blog_list.get(Position).getHname();
        final String ph = blog_list.get(Position).getHph();

        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent=new Intent(context,Visitorsdetails.class);
                    intent.putExtra("hname",name);
                    intent.putExtra("hemail",email);
                    intent.putExtra("hph",ph);

                    context.startActivity(intent);
               }
        });
        viewHolder.setfavtext(name,email,ph);

        //firebaseFirestore = FirebaseFirestore.getInstance();
    }



    public int getItemCount() {
        return blog_list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView named,phoned,emaild;

        private View mview;
        Button btn;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            mview = itemView;
            btn=mview.findViewById(R.id.submit);
            named=mview.findViewById(R.id.hostdisname);
            emaild=mview.findViewById(R.id.hostdisemail);
            phoned=mview.findViewById(R.id.hostdisph);

        }




        public void setfavtext(String name,String email,String ph ) {
           named.setText(name);
            emaild.setText(email);
            //fees.setText("Starts from ₹ "+fee+" /OPD");
            phoned.setText(ph);
           // hospital.setText(hospitals);
        }}}