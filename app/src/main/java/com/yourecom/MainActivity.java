package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.yourecom.data.model.Course;
import com.yourecom.utils.CourseListAdapter;

import java.util.ArrayList;

import android.net.Uri;

import androidx.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;



public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    CourseListAdapter adapter;
    private Context context;
    private ArrayList<Course> courses;

    private static final String TAG = "MainActivity";

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference courseDB = firebaseDatabase.getReference(Course.DB_NAME);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        setFirebaseAdapter();
        setSearchBar();
        setFloatingButton();
    }

    private void setFirebaseAdapter(){

        Query query = courseDB.orderByChild("acronym");

        FirebaseListOptions<Course> options = new FirebaseListOptions.Builder<Course>()
                .setLayout(R.layout.activity_main_list)
                .setQuery(query, Course.class)
                .setLifecycleOwner(this)
                .build();

        FirebaseListAdapter adapter = new FirebaseListAdapter<Course>(options) {
            @Override
            protected void populateView(View convertView, final Course course, int position) {

                // Lookup view for data population
                TextView title = (TextView) convertView.findViewById(R.id.course_title);
                TextView professorName = (TextView) convertView.findViewById(R.id.prof_name);


                // Populate the data into the template view using the data object
                String titleText = course.getAcronym() + " - "+course.getTitle();
                title.setText(titleText);
                professorName.setText(course.getProfessor().getName());

                // Return the completed view to render on screen
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CourseDescriptionActivity.class);
                        intent.putExtra("course_title", course.getTitle());
                        intent.putExtra("course_acronym", course.getAcronym());
                        intent.putExtra("prof_name", course.getProfessor().getName());
                        context.startActivity(intent);
                    }
                });
            }
        };
        simpleList = findViewById(R.id.simpleListView);
        simpleList.setAdapter(adapter);
    }


    private void setFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });
    }

    public void checkCurrentUser() {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }

    public void getUserProfile() {
        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
        // [END get_user_profile]
    }

    public void getProviderData() {
        // [START get_provider_data]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();
            }
        }
        // [END get_provider_data]
    }

    public void sendEmailVerification() {
        // [START send_email_verification]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
        // [END send_email_verification]
    }


    private void setSearchBar(){
        SearchView searchView = findViewById(R.id.search_course);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });

    }
}
