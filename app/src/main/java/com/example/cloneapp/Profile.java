package com.example.cloneapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cloneapp.databinding.ActivityProfileBinding;
import com.example.cloneapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    public ActivityProfileBinding binding;
    FirebaseAuth auth;
    CircleImageView profileImage;
    TextView username,age,gender,location;
    Button like,dislike;
   FirebaseFirestore db;
   String user_gender,user_zipcode,user_id,age1;
   int user_age;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Your Profile");
        auth=FirebaseAuth.getInstance();
        user_id=auth.getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        DocumentReference documentReference=db.collection("users").document(user_id);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                user_gender= (String) value.get("gender");
                user_zipcode=(String) value.get("zipcode");
                age1=(String) value.get("age");
                Log.d("TAG", "onEvent: "+user_gender+" "+user_zipcode+" "+user_age);
                value.getData();
                Log.d("TAG", "dipali"+value.getId() + " => " + value.getData());

            }
        });


        CollectionReference users = db.collection("users");
        //Query userQuery= users.whereEqualTo("username", "prachi");
        Query userQuery = users.whereEqualTo("age",22).whereEqualTo("gender", "female");

        //Query chainedQuery1 = cities.whereEqualTo("state", "CO").whereEqualTo("name", "Denver");
                userQuery.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //User user=document.toObject(User.class);
                                Log.d("TAG", "dipali"+document.getId() + " => " + document.getData());
                                Log.d("name", document.get("username").toString());
                                String Url=document.get("profilepic").toString();
//                                Picasso.get().load(Url).into(binding.profileImage);
//                                Glide.with(getApplicationContext())
//                                        .load(Url)
//                                        .into(binding.profileImage);
                                Picasso.get().load(Url).into(binding.profileImage);
                                binding.username.setText(document.get("username").toString());
                                binding.age.setText(document.get("age").toString());
                                binding.gender.setText(document.get("gender").toString());
                                binding.location.setText(document.get("location").toString());
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                break;
            case R.id.Logout:
                auth.signOut();
                Intent intent=new Intent(Profile.this,SignIn.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}