package com.firstapp.raviraj.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {


    EditText email, password;
    Button sign_in_button, sign_up_button;
    UserLocalStore userLocalStore;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sign_in_button = (Button) findViewById(R.id.sign_in_button);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);

        sign_in_button.setOnClickListener(this);
        sign_up_button.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                User user = new User(null, null);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);

                break;
            case R.id.sign_up_button:
                    startActivity(new Intent(this, SignUpActivity.class));
                    finish();
                break;

        }

    }
}
