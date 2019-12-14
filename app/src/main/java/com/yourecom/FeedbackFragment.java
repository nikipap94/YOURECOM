package com.yourecom;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.yourecom.data.model.Feedback;
import com.yourecom.data.model.Tip;
import com.yourecom.utils.FeedbackListAdapter;
import com.yourecom.utils.TipListAdapter;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {

    public static final int FEEDBACK = 1;
    public static final int TIPS = 2;

    private int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_feedback,container,false);

        ListView lv= (ListView) rootView.findViewById(R.id.feedbackView);

        if(type == FEEDBACK){
            FeedbackListAdapter adapter = new FeedbackListAdapter(this.getActivity(),getFeedbacksByCourse(""));
            lv.setAdapter(adapter);
        }else{
            TipListAdapter adapter = new TipListAdapter(this.getActivity(),getTipsByCourse(""));
            lv.setAdapter(adapter);
        }



        return rootView;
    }

    public FeedbackFragment(int type) {
        super();
        this.type = type;
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

    private ArrayList<Tip> getTipsByCourse(String courseID) {

        ArrayList<Tip> tips=new ArrayList<>();

        tips.add(new Tip("Raphael", "Course super interesting."));
        tips.add(new Tip("Patricia", "Better study hard."));
        tips.add(new Tip("Raphael", "I loved the course."));
        tips.add(new Tip("Bastien", "Course super interesting."));


        return tips;
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
