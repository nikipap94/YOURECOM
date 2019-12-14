package com.yourecom;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.yourecom.utils.MyPagerAdapter;

public class CourseDescriptionActivity extends AppCompatActivity {

    ViewPager vp;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_description);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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

    private void addPages() {
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new FeedbackFragment());
        pagerAdapter.addFragment(new FeedbackFragment());

        //SET ADAPTER TO VP
        vp.setAdapter(pagerAdapter);

    }

}
