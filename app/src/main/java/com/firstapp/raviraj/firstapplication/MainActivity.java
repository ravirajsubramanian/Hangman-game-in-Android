package com.firstapp.raviraj.firstapplication;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;
import java.security.acl.Permission;

public class MainActivity extends AppCompatActivity {

    CardView startButton;
    Button list;
    int notificationID = 1;
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
    private Button notify;
    private Button SQLite;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notify = (Button) findViewById(R.id.list);
        SQLite = (Button) findViewById(R.id.sqlite);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Notification Test");
        mBuilder.setContentText("Hi, This is Android Notification Content");

        Intent resultIntent = new Intent(this, GameActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(GameActivity.class);

        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.


// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);

/*
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                Log.i("gave permission","");

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }*/

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListIntent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(ListIntent);
                finish();
            }
        });
        SQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListIntent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(ListIntent);
                finish();
            }
        });


        /*notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.notify(notificationID, mBuilder.build());
            }
        });*/


        startButton = (CardView) findViewById(R.id.cv_button);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameIntent);
                finish();
            }
        });


    }

    /*Button mail = (Button) findViewById(R.id.mail);
    mail.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"ravirajsubramanian.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Testing");
            i.putExtra(Intent.EXTRA_TEXT, "body of email");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    });*/
}