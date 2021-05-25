package com.example.cloneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.example.cloneapp.Models.NewDeckAdapter;
import com.example.cloneapp.Models.UserModal;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    private SwipeDeck cardStack;
    ArrayList<UserModal> datalist;
    FirebaseFirestore db;
    NewDeckAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        // on below line we are initializing our array list and swipe deck.
        datalist = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        ImageView click=findViewById(R.id.home);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity1.this, Profile.class);
                startActivity(i);
                finish();
            }
        });
        // on below line we are adding data to our array list.
//        courseModalArrayList.add(new CourseModal("C++", "30 days", "20 Tracks", "C++ Self Paced Course", R.drawable.avator));
//        courseModalArrayList.add(new CourseModal("Java", "30 days", "20 Tracks", "Java Self Paced Course", R.drawable.avator));
//        courseModalArrayList.add(new CourseModal("Python", "30 days", "20 Tracks", "Python Self Paced Course", R.drawable.avator));
//        courseModalArrayList.add(new CourseModal("DSA", "30 days", "20 Tracks", "DSA Self Paced Course", R.drawable.avator));
//        courseModalArrayList.add(new CourseModal("PHP", "30 days", "20 Tracks", "PHP Self Paced Course", R.drawable.avator));

        db=FirebaseFirestore.getInstance();
        db.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list){
                    UserModal obj=d.toObject(UserModal.class);
                    datalist.add(obj);
                }
                adapter.notifyDataSetChanged();
            }
        });
       // datalist.add(new UserModal("C++", "30 days", "20 Tracks", "C++ Self Paced Course", "hi","hi",20));
        // on below line we are creating a variable for our adapter class and passing array list to it.

        adapter = new NewDeckAdapter(datalist, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);
        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(MainActivity1.this, "Liked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swipped to right we are displaying a toast message.
                Toast.makeText(MainActivity1.this, "Disliked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(MainActivity1.this, "No more Matches present", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swipped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
    }
}