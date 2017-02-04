package com.android.example.neighbours;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventDetail extends AppCompatActivity {
CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.event_detail_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
    }
}
