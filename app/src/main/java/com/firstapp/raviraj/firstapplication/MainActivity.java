package com.firstapp.raviraj.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    Button start_button;
    TextView tv_title;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_title = (TextView) findViewById(R.id.tv_title);
        start_button = (Button) findViewById(R.id.start_button);

        start_button.setOnClickListener(this);
        }

    public void onClick(View v) {
        startActivity(new Intent(this, SignInActivity.class));
    }
}
