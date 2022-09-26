package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private ActivityMainBinding binding;
    private RecyclerView rvContacts;

    private ArrayList<Contact> contactsList;
    private ContactsAdapter contactsAdapter;
    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(view);
        rvContacts = findViewById(R.id.rv_contacts);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDao = appDatabase.contactDao();

//                contactDao.insert(new Contact("Nguyen Van A","0923123542","a@gmail.com"));
            }
        });


        contactsList = new ArrayList<Contact>();

        contactsList.add(new Contact("Nguyen Van A", "0923123542","a@gmail.com"));
        contactsList.add(new Contact("Nguyen Van B", "0923123531","b@gmail.com"));
        contactsList.add(new Contact("Nguyen Van C", "0923123612","c@gmail.com"));
        contactsAdapter.notifyDataSetChanged();

        contactsAdapter = new ContactsAdapter(contactsList, MainActivity.this);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setHasFixedSize(true);
        rvContacts.setAdapter(contactsAdapter);
    }
    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.top_app_bar_main, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.search);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Contact> filteredlist = new ArrayList<Contact>();

        // running a for loop to compare elements.
        for (Contact item : contactsList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            contactsAdapter.filterList(filteredlist);
        }
    }

}