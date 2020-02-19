package com.example.pr10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
>>>>>>> origin/master
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

<<<<<<< HEAD

public class MainActivity extends AppCompatActivity {

=======
public class MainActivity extends AppCompatActivity {

    private Button viewData;
    private Button insertData;
    private EditText name;
    private EditText number;
>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
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
=======

        viewData = findViewById(R.id.viewData);
        insertData = findViewById(R.id.insertData);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "db-contacts")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
        final ContactDAO contactDAO = database.getContactDAO();
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Contact> allcontacts = contactDAO.getContacts();
                Iterator<Contact> iterator = allcontacts.iterator();
                String mystring = "";
                while(iterator.hasNext()){
                    Contact temp =iterator.next();
                    mystring += temp.getName() + " " + temp.getPhoneNumber() + "\n";
                }
                Toast.makeText(getApplicationContext(),mystring,Toast.LENGTH_SHORT).show();
            }
        });
        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.setName(name.getText().toString());
                contact.setPhoneNumber(number.getText().toString());
                contactDAO.insert(contact);
            }
        });
    }


>>>>>>> origin/master
}
