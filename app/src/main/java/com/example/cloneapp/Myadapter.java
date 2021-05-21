package com.example.cloneapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Myadapter extends RecyclerView.Adapter<Myadapter.myviewholder> {

    UserRecycler userRecycler = new UserRecycler();
    ArrayList<model> datalist;
    View view;
    public Myadapter(ArrayList<model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_profile,parent,false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.username.setText(datalist.get(position).getUsername());
       // holder.age.setText(datalist.get(position).getAge());
        holder.location.setText(datalist.get(position).getLocation());
        holder.gender.setText(datalist.get(position).getGender());
        int age=datalist.get(position).getAge();
        String age1= String.valueOf(age);
        String Url=datalist.get(position).getProfilepic();
        Picasso.get().load(Url).into(holder.profile_image);
        holder.age.setText(age1);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }


    class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView profile_image;
        TextView age,gender,username,location;
        Button like,dislike;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
           // age=itemView.findViewById(R.id.age);
            gender=itemView.findViewById(R.id.gender);
            username=itemView.findViewById(R.id.username);
            location=itemView.findViewById(R.id.postal);
            age=itemView.findViewById(R.id.age);
            like=itemView.findViewById(R.id.like);
            dislike=itemView.findViewById(R.id.dislike);
            profile_image=itemView.findViewById(R.id.profile_image);

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "Liked", Toast.LENGTH_SHORT).show();
                }
            });
            dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "Disliked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
