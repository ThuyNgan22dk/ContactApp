package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private ArrayList<Contact> contactsList;
    private ContactsAdapter contactsAdapter;
    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactsList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(contactsList);
        binding.rvContacts.setAdapter(contactsAdapter);

        contactsList.add(new Contact("Nguyen Van A", "0923123542","a@gmail.com"));
        contactsList.add(new Contact("Nguyen Van B", "0923123531","b@gmail.com"));
        contactsList.add(new Contact("Nguyen Van C", "0923123612","c@gmail.com"));
        contactsAdapter.notifyDataSetChanged();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDao = appDatabase.contactDao();

//                contactDao.insert(new Contact("Nguyen Van A","0923123542","a@gmail.com"));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar_main, menu);

        return true;
    }
}