package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class NewContactActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();
        context = this;

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.top_app_bar, menu);

        // below line is to get our menu item.
        MenuItem closeItem = menu.findItem(R.id.close);
        closeItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        MenuItem saveItem = menu.findItem(R.id.save);
        saveItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });
        return true;
    }
}