package com.example.cloneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.cloneapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
ActivitySignInBinding binding;
ProgressDialog progressDialog;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(SignIn.this);
        progressDialog.setTitle("Siging In");
        progressDialog.setMessage("We are redirecting you to Profile, please wait!");
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.username.getText().toString(),binding.password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(SignIn.this,Profile.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(SignIn.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setTitle("Signin");
    }

}