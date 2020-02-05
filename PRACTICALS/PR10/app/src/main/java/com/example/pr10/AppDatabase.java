package com.example.pr10;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import java.util.List;

@Entity(tableName = "contact")
class Contact {
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
