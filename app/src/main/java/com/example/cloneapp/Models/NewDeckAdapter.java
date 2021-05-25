package com.example.cloneapp.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cloneapp.CourseModal;
import com.example.cloneapp.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class NewDeckAdapter extends BaseAdapter {
    private ArrayList<UserModal> userData;
    private Context context;

    // on below line we have created constructor for our variables.
    public NewDeckAdapter(ArrayList<UserModal> userData, Context context) {
        this.userData = userData;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return userData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return userData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.idName)).setText(userData.get(position).getUsername());
       // ((TextView) v.findViewById(R.id.idAge)).setText(userData.get(position).getAge());
        ((TextView) v.findViewById(R.id.idGender)).setText(userData.get(position).getGender());
        ((TextView) v.findViewById(R.id.idLocation)).setText(userData.get(position).getZipcode());
        int age=userData.get(position).getAge();
        String age1= String.valueOf(age);
        String Url=userData.get(position).getProfilepic();
        Picasso.get().load(Url).into((ImageView) v.findViewById(R.id.profile_image));
        ((TextView) v.findViewById(R.id.idAge)).setText(age1);
        return v;
    }
}
