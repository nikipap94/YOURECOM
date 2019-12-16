package com.yourecom.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yourecom.R;
import com.yourecom.data.model.Feedback;
import com.yourecom.data.model.Tip;

import java.util.ArrayList;

public class TipListAdapter extends BaseAdapter {

    Context c;
    ArrayList<Tip> tips;
    LayoutInflater inflater;

    public TipListAdapter(Context c, ArrayList<Tip> tips) {
        this.c = c;
        this.tips = tips;
    }

    @Override
    public int getCount() {
        return tips.size();
    }

    @Override
    public Object getItem(int position) {
        return tips.get(position);
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
            convertView=inflater.inflate(R.layout.fragment_tip_list,parent,false);
        }

        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);

        final String nameStr = tips.get(position).getAuthorName();
        final String textStr = tips.get(position).getText();
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

