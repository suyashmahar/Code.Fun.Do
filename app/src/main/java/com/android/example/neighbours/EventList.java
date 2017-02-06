package com.android.example.neighbours;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventList extends AppCompatActivity implements ClickListener{
    RecyclerView eventsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateEvents();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(view.getContext(), CreateEvent.class);
                startActivity(createIntent);
            }
        });


    }

    public void populateEvents(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("commuities/sample_community/events");

        eventsRecyclerView = (RecyclerView) findViewById(R.id.content_event_list_recyler);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        eventsRecyclerView.setLayoutManager(eventLayoutManager);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Map> post = (ArrayList<Map>) dataSnapshot.getValue();
                List<Events> eventList = new ArrayList<Events>();

                int eventCount = 0;
                for (Map<String, String> event : post){
                    if (eventCount < 5) {
                        eventList.add(new Events(event.get("description"),
                                (String)event.get("id"),
                                event.get("image"),
                                "sample_community",
                                event.get("organizer"),
                                event.get("time"),
                                event.get("title"),
                                event.get("votes")
                        ));
                        eventCount++;
                    } else {
                        break;
                    }
                }

                EventListAdapter eventAdapter = new EventListAdapter(eventList);
                eventsRecyclerView.setAdapter(eventAdapter);
                eventAdapter.setClickListener(EventList.this);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {
        ImageView img=(ImageView)view.findViewById(R.id.event_tile_image);
        TextView title=(TextView)view.findViewById(R.id.event_tile_title);
        TextView description=(TextView)view.findViewById(R.id.event_tile_description);
        TextView date=(TextView)view.findViewById(R.id.event_tile_rel_time);
        TextView hearts=(TextView)view.findViewById(R.id.event_tile_hearts);
        TextView comments=(TextView)view.findViewById(R.id.event_tile_comments_count);

        Intent i=new Intent(EventList.this,EventDetail.class);

        i.putExtra("EventName",title.getText().toString());
        i.putExtra("EventDescription",description.getText().toString());
        i.putExtra("EventTime",date.getText().toString());
        i.putExtra("EventHearts",hearts.getText().toString());
        i.putExtra("EventComments",comments.getText().toString());
        startActivity(i);
    }
}
