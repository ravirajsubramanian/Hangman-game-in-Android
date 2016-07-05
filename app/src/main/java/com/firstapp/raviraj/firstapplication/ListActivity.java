package com.firstapp.raviraj.firstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import java.util.List;

public class ListActivity extends Activity {

    //Android listview object
    ListView listViewPhoneBook;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);


        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_view, menu);
        return true;
    }
}
/*
        //get the ListView Reference from xml file
        listViewPhoneBook = (ListView) findViewById(R.id.listPhoneBook);

        //arrayColumns is the array which will contain all contacts name in your cursor, where the cursor will get the data from contacts database.
        //Here we are displaying name only from the contacts database
        String[] arrayColumns = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
        //arrayViewID is the id of the view it will map to here textViewName only , you can add more Views as per Requirement
        int[] arrayViewID = new int[]{R.id.textViewName};

        //reference to the phone contacts database using  Cursor and URI in android.
        Cursor cursor;
        cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

    *//*Create an Adapter with arguments layoutID, Cursor, Array Of Columns, and Array of Views which is to be Populated
    This adapter will be associated with the listview to populate items directly. So this adapter is associated with
    the each_contact.xlm file to view in a activity *//*
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.each_contact, cursor, arrayColumns, arrayViewID);
        listViewPhoneBook.setAdapter(adapter);

     *//*this is extra code for click event of any item in the list view.
     when you will click on na contact's name in the list view. it will give you the item name and position in a listview.
     Note that: if you want to query the contacts name details(like phone number for the clicked contact name  & all details,
     we need to query it again. i will explain it in my a separate post in my blog.*//*

        // To handle the click on List View Item
        listViewPhoneBook.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                // position parameter gives the index or position of ListView Item which is Clicked
                TextView tv = (TextView) v.findViewById(R.id.textViewName);
                String name = tv.getText().toString();
                Toast.makeText(getApplicationContext(), "Contact Selected: " + name, Toast.LENGTH_LONG).show();
            }
        });

    }
} */
