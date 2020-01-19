package com.yourecom.utils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.yourecom.CourseDescriptionActivity;
import com.yourecom.R;
import com.yourecom.data.model.Course;
import com.yourecom.data.model.Feedback;

import java.util.ArrayList;

import androidx.lifecycle.LifecycleOwner;

public class FeedbackListAdapter extends BaseAdapter {

    Context c;
    ArrayList<Feedback> feedbacks;
    LayoutInflater inflater;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference feedbackRef = firebaseDatabase.getReference(Feedback.DB_NAME);

    public FeedbackListAdapter(final Context c) {
        this.c = c;
        this.feedbacks = new ArrayList<Feedback>();


        Query query = feedbackRef.orderByChild("date");

        FirebaseListOptions<Feedback> options = new FirebaseListOptions.Builder<Feedback>()
                .setLayout(R.layout.fragment_feedback_list)
                .setQuery(query, Feedback.class)
                .setLifecycleOwner((LifecycleOwner) c)
                .build();

        FirebaseListAdapter adapter = new FirebaseListAdapter<Feedback>(options) {
            @Override
            protected void populateView(View convertView, final Feedback feedback, int position) {

                TextView author = (TextView) convertView.findViewById(R.id.author);
                TextView text = (TextView) convertView.findViewById(R.id.text);
                RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);

                final String nameStr = feedbacks.get(position).getAuthorName();
                final String textStr = feedbacks.get(position).getText();
                final int rating = feedbacks.get(position).getRating();
//
                author.setText(nameStr);
                text.setText(textStr);
                ratingBar.setRating(rating);
            }
        };
        
    }

    @Override
    public int getCount() {
        return feedbacks.size();
    }

    @Override
    public Object getItem(int position) {
        return feedbacks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.fragment_feedback_list,parent,false);
        }

        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);

        final String nameStr = feedbacks.get(position).getAuthorName();
        final String textStr = feedbacks.get(position).getText();
        final int rating = feedbacks.get(position).getRating();

        author.setText(nameStr);
        text.setText(textStr);
        ratingBar.setRating(rating);


        return convertView;
    }

}

