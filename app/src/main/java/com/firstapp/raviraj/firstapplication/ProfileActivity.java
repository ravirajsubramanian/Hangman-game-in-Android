package com.firstapp.raviraj.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    EditText name, email;
    Button logout_button;
    UserLocalStore userLocalStore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        logout_button = (Button) findViewById(R.id.logout_button);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);

        logout_button.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    protected void onStart(){
        super.onStart();
        if (authentication() == true){
            displayUserDetails();
        } else{
            startActivity(new Intent(ProfileActivity.this, SignInActivity.class));
            finish();
        }
    }

    public boolean authentication(){
        return userLocalStore.getUserLoggedIn();
    }

    public void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
        name.setText(user.name);
        email.setText(user.email);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_button:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, SignInActivity.class));
                break;
        }
    }
}
