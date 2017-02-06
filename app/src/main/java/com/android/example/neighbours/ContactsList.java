package com.android.example.neighbours;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Harshit Bansal on 2/6/2017.
 */

public class ContactsList extends AppCompatActivity {
    RecyclerView contactsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        contactsList=(RecyclerView)findViewById(R.id.contacts_list);
    }
}
