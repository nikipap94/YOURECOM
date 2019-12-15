package com.yourecom;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.yourecom.utils.MyPagerAdapter;

public class CourseDescriptionActivity extends AppCompatActivity {

    ViewPager vp;
    TabLayout tabLayout;
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_description);

        setDescription();


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = tabLayout.getSelectedTabPosition();
                Intent intent;

                switch(position) {
                    case 0:
                        // feedback tab is selected
                        intent = new Intent(CourseDescriptionActivity.this, AddFeedbackActivity.class);
                        intent.putExtra("course", course);
                        CourseDescriptionActivity.this.startActivityForResult(intent, 0);
                        break;
                    case 1:
                        // tip tab is selected
                        intent = new Intent(CourseDescriptionActivity.this, AddTipsActivity.class);
                        intent.putExtra("course", course);
                        CourseDescriptionActivity.this.startActivityForResult(intent, 0);
                        break;
                }


            }
        });

        //VIEWPAGER
        vp= (ViewPager) findViewById(R.id.CD_Viewpager);
        this.addPages();

        //TABLAYOUT
        tabLayout= (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabLayout.setOnTabSelectedListener(this);
    }

    private void setDescription(){
        Intent intent = getIntent();
        String title = intent.getStringExtra("course_title");
        String acr = intent.getStringExtra("course_acronym");
        String prof = intent.getStringExtra("prof_name");

        this.course = acr + " " + title;

        TextView titleTxtView = (TextView) findViewById(R.id.courseName);
        TextView profTxtView = (TextView) findViewById(R.id.professorName);

        titleTxtView.setText(acr + " - " + title);
        profTxtView.setText(prof);

    }

    private void addPages() {
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new FeedbackFragment(FeedbackFragment.FEEDBACK));
        pagerAdapter.addFragment(new FeedbackFragment(FeedbackFragment.TIPS));

        //SET ADAPTER TO VP
        vp.setAdapter(pagerAdapter);

    }

}
