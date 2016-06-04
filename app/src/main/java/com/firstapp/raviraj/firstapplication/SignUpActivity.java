package com.firstapp.raviraj.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etName, etEmail, etPassword;
    Button sign_in_button, sign_up_button;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);
        sign_in_button = (Button) findViewById(R.id.sign_in_button);

        sign_up_button.setOnClickListener(this);
        sign_in_button.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_button:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                User RegisteredData = new User(name, email, password);
                break;
            case R.id.sign_in_button:
                    startActivity(new Intent(this, SignInActivity.class));
                    finish();
                break;


        }
    }
}
