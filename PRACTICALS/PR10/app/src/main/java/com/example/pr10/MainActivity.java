package com.example.pr10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        ContactDAO contactDAO = database.getContactDAO();

//        //Inserting a contact
//        Contact contact = new Contact();
//        contact.setFirstName("Gurleen");
//        contact.setLastName("Sethi");
//        contact.setPhoneNumber("1234567890");
////
//        contactDAO.insert(contact);

        //Fetching all contacts
        Contact allcontact = contactDAO.getContactWithId("1234567890");

//        while (iterator.hasNext()){
            Toast.makeText(getApplicationContext(),allcontact.getFirstName(),Toast.LENGTH_SHORT).show();
//        }

    }
}
