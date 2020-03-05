package com.example.pr10;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

@Entity(tableName = "contact")
class Contact {
    private String firstName;
    private String lastName;
    @PrimaryKey
    @NonNull
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phoneNumber;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

<<<<<<< HEAD
    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
@Dao
interface ContactDAO {
    @Insert
    public void insert(Contact... contacts);

    @Update
    public void update(Contact... contacts);

    @Delete
    public void delete(Contact contact);

    @Query("SELECT * FROM contact")
    public List<Contact> getContacts();

    @Query("SELECT * FROM contact WHERE phoneNumber = :number")
    public Contact getContactWithId(String number);
}
@Database(entities = {Contact.class}, version = 1)
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}

@Dao
interface ContactDAO {
    @Insert
    void insert(Contact... contacts);
    @Query("SELECT * FROM contact")
    List<Contact> getContacts();
}
@Database(entities = {Contact.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
