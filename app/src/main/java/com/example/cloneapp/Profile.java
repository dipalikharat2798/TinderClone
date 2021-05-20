package com.example.cloneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.cloneapp.databinding.ActivityProfileBinding;
import com.example.cloneapp.databinding.ActivitySignInBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    public ActivityProfileBinding binding;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_profile);
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Your Profile");
        auth=FirebaseAuth.getInstance();
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