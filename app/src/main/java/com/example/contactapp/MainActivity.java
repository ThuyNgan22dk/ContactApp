package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    private ActivityMainBinding binding;
    private Context context;
//    private RecyclerView rvContacts;
//    private FloatingActionButton btnAdd;
//    private ArrayList<Contact> contactsList;
//    private ContactsAdapter contactsAdapter;
    private EditText edtInput, edtAction;
    private Button btnHistory, btnSubmit;
    private TextView tvOutput;
    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

//        rvContacts = findViewById(R.id.rv_contacts);
//        btnAdd = findViewById(R.id.btn_add);
        edtInput = findViewById(R.id.edt_input);
        edtAction = findViewById(R.id.edt_action);
        tvOutput = findViewById(R.id.tv_output);
        btnHistory = findViewById(R.id.btn_history);
        btnSubmit = findViewById(R.id.btn_submit);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDao = appDatabase.contactDao();
//                contactDao.insert(new Contact("Nguyen Van A","0923123542","a@gmail.com"));
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //kiem tra thong tin
                        if (edtInput.getText().toString().equals("") || edtAction.getText().toString().equals("")){
                            Toast.makeText(MainActivity.this,"Moi ban nhap du lieu",Toast.LENGTH_SHORT).show();
                        }else{
                            //dem so luong chu va so
                            String arrInput = edtInput.getText().toString();

                            int letterCount = 0, digitCount = 0, wordCount = 0;
                            for(int i=0; i < arrInput.length(); i++){
                                if(Character.isLetter(arrInput.charAt(i))) letterCount++;
                                else if(Character.isDigit(arrInput.charAt(i))) digitCount++;
                            }
                            String[] strArr = arrInput.split("\\s",0);
                            for (String strArr1 : strArr) {
                                if (!strArr1.isEmpty())
                                    wordCount++;
                            }
                        }

                    }
                });

                //xem lich su
                btnHistory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, HistoryActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
















//        buildRecyclerView();
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, NewContactActivity.class);
//                startActivity(intent);
//            }
//        });
    }

//    Contactapp
    // calling on create option menu
    // layout to inflate our menu file.
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // below line is to get our inflater
//        MenuInflater inflater = getMenuInflater();
//
//        // inside inflater we are inflating our menu file.
//        inflater.inflate(R.menu.top_app_bar_main, menu);
//
//        // below line is to get our menu item.
//        MenuItem searchItem = menu.findItem(R.id.search);
//
//        // getting search view of our item.
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        // below line is to call set on query text listener method.
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // inside on query text change method we are
//                // calling a method to filter our recycler view.
//                filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }
//
//    private void filter(String text) {
//        // creating a new array list to filter our data.
//        ArrayList<Contact> filteredlist = new ArrayList<Contact>();
//
//        // running a for loop to compare elements.
//        for (Contact item : contactsList) {
//            // checking if the entered string matched with any item of our recycler view.
//            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
//                // if the item is matched we are
//                // adding it to our filtered list.
//                filteredlist.add(item);
//            }
//        }
//        if (filteredlist.isEmpty()) {
//            // if no item is added in filtered list we are
//            // displaying a toast message as no data found.
//            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
//        } else {
//            // at last we are passing that filtered
//            // list to our adapter class.
//            contactsAdapter.filterList(filteredlist);
//        }
//    }
//
//    private void buildRecyclerView() {
//        // below line we are creating a new array list
//        contactsList = new ArrayList<Contact>();
//
//        // below line is to add data to our array list.
//        contactsList.add(new Contact("Nguyen Van A", "0923123542","a@gmail.com"));
//        contactsList.add(new Contact("Van A", "0923323542","b@gmail.com"));
//        contactsList.add(new Contact("Nguyen B", "0903123542","c@gmail.com"));
//        contactsList.add(new Contact("Van C", "0923553542","d@gmail.com"));
//        contactsList.add(new Contact("The D", "0923993542","e@gmail.com"));
//
//        // initializing our adapter class.
//        contactsAdapter = new ContactsAdapter(contactsList, MainActivity.this);
//
//        // adding layout manager to our recycler view.
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        rvContacts.setHasFixedSize(true);
//
//        // setting layout manager
//        // to our recycler view.
//        rvContacts.setLayoutManager(manager);
//
//        // setting adapter to
//        // our recycler view.
//        rvContacts.setAdapter(contactsAdapter);
//    }
}