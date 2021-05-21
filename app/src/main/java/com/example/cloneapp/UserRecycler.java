package com.example.cloneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserRecycler extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<model> datalist;
FirebaseFirestore db;
Myadapter myadapter;
Button like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recycler);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         like=findViewById(R.id.like);
//        dislike=findViewById(R.id.dislike);

        datalist=new ArrayList<>();
        myadapter=new Myadapter(datalist);
        recyclerView.setAdapter(myadapter);
        db=FirebaseFirestore.getInstance();
        db.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
             List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();
             for(DocumentSnapshot d:list){
                 model obj=d.toObject(model.class);
                 datalist.add(obj);
             }
             myadapter.notifyDataSetChanged();
            }
        });


    }
}