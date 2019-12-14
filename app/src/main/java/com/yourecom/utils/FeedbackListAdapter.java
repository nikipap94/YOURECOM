package com.yourecom.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yourecom.R;
import com.yourecom.data.model.Feedback;

import java.util.ArrayList;

public class FeedbackListAdapter extends BaseAdapter {

    Context c;
    ArrayList<Feedback> feedbacks;
    LayoutInflater inflater;

    public FeedbackListAdapter(Context c, ArrayList<Feedback> feedbacks) {
        this.c = c;
        this.feedbacks = feedbacks;
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

        final String nameStr = feedbacks.get(position).getAuthorName();
        final String textStr = feedbacks.get(position).getText();
//
        author.setText(nameStr);
        text.setText(textStr);

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(c,name,Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }
}

