package com.example.contactapp;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Insert
    void insert(Contact... contacts);
}
