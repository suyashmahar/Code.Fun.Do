package com.android.example.neighbours;

import android.app.Notification;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationList extends AppCompatActivity implements ClickListener{
    RecyclerView notificationRecyclerView;
    private ClickListener clickListener=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(view.getContext(), CreateNotification.class);
                startActivity(createIntent);
            }
        });

        notificationRecyclerView = (RecyclerView) findViewById(R.id.notification_list_recyclerview);

        populateNotifications();

    }


    public void populateNotifications(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("commuities/sample_community/notifications");

        notificationRecyclerView = (RecyclerView) findViewById(R.id.notification_list_recyclerview);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        notificationRecyclerView .setLayoutManager(eventLayoutManager);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Map> post = (ArrayList<Map>) dataSnapshot.getValue();
                List<NotificationItem> notificationList = new ArrayList<NotificationItem>();

                int campaignCount = 0;
                for (Map<String, String> campaign : post){
                    if (campaignCount < 5) {
                        notificationList.add(new NotificationItem(campaign.get("description"),
                                campaign.get("time"),
                                campaign.get("title")
                        ));
                    } else {
                        break;
                    }
                }

                NotificationListAdapter notificationAdapter = new NotificationListAdapter(notificationList);
                notificationRecyclerView.setAdapter(notificationAdapter);
                notificationAdapter.setClickListener(NotificationList.this);
                notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {

        TextView title=(TextView)view.findViewById(R.id.notification_tile_title);
        TextView description=(TextView)view.findViewById(R.id.notification_tile_description);
        TextView date=(TextView)view.findViewById(R.id.notification_tile_date);

        Intent i=new Intent(NotificationList.this,NotificationDetail.class);

        i.putExtra("NotificationTitle",title.toString());
        i.putExtra("NotificationDescription",description.toString());
        i.putExtra("NotificationTime",date.toString());

        startActivity(i);
    }
}
