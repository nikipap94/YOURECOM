package com.yourecom;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.yourecom.data.model.Feedback;
import com.yourecom.utils.FeedbackListAdapter;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_feedback,container,false);

        ListView lv= (ListView) rootView.findViewById(R.id.feedbackView);
        FeedbackListAdapter adapter = new FeedbackListAdapter(this.getActivity(),getFeedbacksByCourse(""));
        lv.setAdapter(adapter);

        return rootView;
    }

    private ArrayList<Feedback> getFeedbacksByCourse(String courseID) {

        ArrayList<Feedback> feedbacks=new ArrayList<>();

        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));
        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));
        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));
        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));
        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));
        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));
        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));
        feedbacks.add(new Feedback("Raphael", "I loved the course.", 4));

        return feedbacks;
    }

    @Override
    public String toString() {
        return "Feedback";
    }

}
