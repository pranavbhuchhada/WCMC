package com.example.pr10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button viewData;
    private Button insertData;
    private EditText name;
    private EditText number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


}
