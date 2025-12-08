package com.example.lab3bai1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        db = new DatabaseHandler(this);

        // Optional: Clear existing contacts if you want to reset data each run
        // getWritableDatabase().execSQL("DELETE FROM contacts");

        // Inserting Contacts
        Log.d("Insert:", "Inserting contacts...");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading:", "Reading all contacts...");
        List<Contact> contacts = db.getAllContacts();

        // Preparing data for display
        List<String> contactDisplayList = new ArrayList<>();
        for (Contact cn : contacts) {
            String log = "ID: " + cn.getID() + ", Name: " + cn.getName() + ", Phone: " + cn.getPhoneNumber();
            contactDisplayList.add(log);
            Log.d("Contact:", log);
        }

        // Display in ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                contactDisplayList
        );
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Contact contactToDelete = contacts.get(position);

            db.deleteContact(contactToDelete);
            Toast.makeText(this,
                    "Đã xóa: " + contactToDelete.getName(),
                    Toast.LENGTH_SHORT).show();

            contacts.remove(position);
            contactDisplayList.remove(position);
            adapter.notifyDataSetChanged();

            return true;
        });
    }
}
