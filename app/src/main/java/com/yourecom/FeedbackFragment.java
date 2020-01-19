package com.yourecom;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.yourecom.data.model.Feedback;
import com.yourecom.data.model.Tip;
import com.yourecom.utils.FeedbackListAdapter;
import com.yourecom.utils.TipListAdapter;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {

    public static final int FEEDBACK = 1;
    public static final int TIPS = 2;

    private int type;
    private String courseId;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference feedbackRef = firebaseDatabase.getReference(Feedback.DB_NAME);
    private final DatabaseReference tipRef = firebaseDatabase.getReference(Tip.DB_NAME);

    public FeedbackFragment(int type, String courseId) {
        super();
        this.type = type;
        this.courseId = courseId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_feedback,container,false);

        ListView lv= (ListView) rootView.findViewById(R.id.feedbackView);

        if(type == FEEDBACK){
            lv.setAdapter(getFeedbackAdapter());
        }else{
            lv.setAdapter(getTipAdapter());
        }



        return rootView;
    }
    public FirebaseListAdapter getFeedbackAdapter() {
        Query query = feedbackRef.orderByChild("courseId").endAt(this.courseId, "courseId");

        FirebaseListOptions<Feedback> options = new FirebaseListOptions.Builder<Feedback>()
                .setLayout(R.layout.fragment_feedback_list)
                .setQuery(query, Feedback.class)
                .setLifecycleOwner(getActivity())
                .build();

        FirebaseListAdapter adapter = new FirebaseListAdapter<Feedback>(options) {
            @Override
            protected void populateView(View convertView, final Feedback feedback, int position) {

                TextView author = (TextView) convertView.findViewById(R.id.author);
                TextView text = (TextView) convertView.findViewById(R.id.text);
                RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);

                final String nameStr = feedback.getAuthorName();
                final String textStr = feedback.getText();
                final int rating = feedback.getRating();

                author.setText(nameStr);
                text.setText(textStr);
                ratingBar.setRating(rating);
            }
        };

        return adapter;
    }

    public FirebaseListAdapter getTipAdapter() {
        Query query = tipRef.orderByKey();

        FirebaseListOptions<Tip> options = new FirebaseListOptions.Builder<Tip>()
                .setLayout(R.layout.fragment_tip_list)
                .setQuery(query, Tip.class)
                .setLifecycleOwner(getActivity())
                .build();

        FirebaseListAdapter adapter = new FirebaseListAdapter<Tip>(options) {
            @Override
            protected void populateView(View convertView, final Tip tip, int position) {

                TextView author = (TextView) convertView.findViewById(R.id.author);
                TextView text = (TextView) convertView.findViewById(R.id.text);

                final String nameStr = tip.getAuthorName();
                final String textStr = tip.getText();
//
                author.setText(nameStr);
                text.setText(textStr);
            }
        };

        return adapter;
    }


    @Override
    public String toString() {
        if(type == FEEDBACK){
            return "Feedback";
        }else{
            return "Tips";
        }

    }

}
