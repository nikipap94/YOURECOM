package com.yourecom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private int counter = 3;
    private EditText name;
    EditText password;
    TextView info;
    Button login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.edName);
        password = (EditText) findViewById(R.id.edPassword);
        info = (TextView) findViewById(R.id.textView);
        login = (Button) findViewById(R.id.btLogin);
        signup = (Button) findViewById(R.id.btSignUp);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(), password.getText().toString());
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }


     private void validate(String username, String userpassword) {
        if ((username.endsWith("@eurecom.fr")) && (userpassword.equals(this.password))) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else{
            counter--;
            info.setText("Number of remaining attempts:" + String.valueOf(counter));
            if (counter == 0) {
                login.setEnabled(false);
            }

        }
    }
}
