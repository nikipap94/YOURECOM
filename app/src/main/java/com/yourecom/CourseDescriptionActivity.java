package com.yourecom;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yourecom.data.model.Course;
import com.yourecom.data.model.Feedback;
import com.yourecom.utils.MyPagerAdapter;

public class CourseDescriptionActivity extends AppCompatActivity {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference courseRef = firebaseDatabase.getReference(Course.DB_NAME);
    ViewPager vp;
    TabLayout tabLayout;
    String course;
    String courseKey;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_description);

        getSupportActionBar().setTitle("Description");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setDescription();
        ratingBar = (RatingBar) findViewById(R.id.rating_course);
        getCourseRating();


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = tabLayout.getSelectedTabPosition();
                Intent intent;

                switch (position) {
                    case 0:
                        // feedback tab is selected
                        intent = new Intent(CourseDescriptionActivity.this, AddFeedbackActivity.class);
                        intent.putExtra("courseKey", courseKey);
                        intent.putExtra("courseTitle", course);
                        CourseDescriptionActivity.this.startActivityForResult(intent, 0);
                        break;
                    case 1:
                        // tip tab is selected
                        intent = new Intent(CourseDescriptionActivity.this, AddTipsActivity.class);
                        intent.putExtra("courseKey", courseKey);
                        intent.putExtra("courseTitle", course);
                        CourseDescriptionActivity.this.startActivityForResult(intent, 0);
                        break;
                }


            }
        });

        //VIEWPAGER
        vp = (ViewPager) findViewById(R.id.CD_Viewpager);
        this.addPages();

        //TABLAYOUT
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeAsUp:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setDescription() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("course_title");
        String acr = intent.getStringExtra("course_acronym");
        String prof = intent.getStringExtra("prof_name");

        this.course = acr + " " + title;

        this.courseKey = intent.getStringExtra("course_key");

        TextView titleTxtView = (TextView) findViewById(R.id.courseName);
        TextView profTxtView = (TextView) findViewById(R.id.professorName);

        titleTxtView.setText(acr + " - " + title);
        profTxtView.setText(prof);

    }

    private void addPages() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new FeedbackFragment(FeedbackFragment.FEEDBACK, this.courseKey));
        pagerAdapter.addFragment(new FeedbackFragment(FeedbackFragment.TIPS, this.courseKey));

        //SET ADAPTER TO VP
        vp.setAdapter(pagerAdapter);

    }

    private void getCourseRating() {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Course course = dataSnapshot.getValue(Course.class);
                ratingBar.setRating(course.getAverageRating());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        courseRef.child(courseKey).addValueEventListener(listener);

    }


}
