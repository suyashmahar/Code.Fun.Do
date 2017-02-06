package com.android.example.neighbours;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    RecyclerView eventsRecyclerView;
    RecyclerView campaignRecyclerView;
    RecyclerView notificationRecyclerView;
    RecyclerView complaintsRecyclerView;
    int campaignCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try {
            populateEvents();
            populateCampaigns();
            populateNotifications();
            populateComplaints();
        } catch (Exception e){
            Toast.makeText(this, "Error fetching data from server", Toast.LENGTH_LONG).show();
        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        Button moreEventsButton = (Button)findViewById(R.id.activity_home_more_events_button);
        moreEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(view.getContext(), EventList.class );
                startActivity(newIntent);
                /*
                Uploader uploader = new Uploader(getApplicationContext());
                Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");

                final DatabaseReference ref = database.getReference("commuities/sample_community/events/" + campaignCount);
                Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");
                ref.setValue(eventToAdd);
                campaignCount++;

                uploader.createAndPushEvent(eventToAdd);
            */
            }
        });
        Button moreCampaignsButton = (Button)findViewById(R.id.activity_home_more_campaign_button);
        moreCampaignsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(view.getContext(), CampaignList.class );
                startActivity(newIntent);
                /*
                Uploader uploader = new Uploader(getApplicationContext());
                Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");

                final DatabaseReference ref = database.getReference("commuities/sample_community/events/" + campaignCount);
                Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");
                ref.setValue(eventToAdd);
                campaignCount++;

                uploader.createAndPushEvent(eventToAdd);
            */
            }
        });
        Button moreNotificationButton = (Button)findViewById(R.id.activity_home_more_notice_button);
        moreNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(view.getContext(), NotificationList.class );
                startActivity(newIntent);
                /*
                Uploader uploader = new Uploader(getApplicationContext());
                Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");

                final DatabaseReference ref = database.getReference("commuities/sample_community/events/" + campaignCount);
                Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");
                ref.setValue(eventToAdd);
                campaignCount++;

                uploader.createAndPushEvent(eventToAdd);
            */
            }
        });
        Button moreNoticesButton =(Button)findViewById(R.id.activity_home_more_notice_button);
        moreNoticesButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_LONG).show();
               Intent newIntent = new Intent(v.getContext(), NotificationList.class );
               startActivity(newIntent);
           }
        });
    }



    public void populateEvents(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("commuities/sample_community/events");

        eventsRecyclerView = (RecyclerView) findViewById(R.id.activity_home_events_recycler);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
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

                MainActivityEventAdapter eventAdapter = new MainActivityEventAdapter(eventList);
                eventsRecyclerView.setAdapter(eventAdapter);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void populateCampaigns(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("commuities/sample_community/campaigns");

        campaignRecyclerView = (RecyclerView) findViewById(R.id.activity_home_campaign_recycler);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        campaignRecyclerView.setLayoutManager(eventLayoutManager);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Map> post = (ArrayList<Map>) dataSnapshot.getValue();
                List<Campaign> campaignList = new ArrayList<Campaign>();

                int campaignCount = 0;
                for (Map<String, String> campaign : post){
                    if (campaignCount < 5) {
                        campaignList.add(new Campaign(campaign.get("description"),
                                campaign.get("total_funds"),
                                campaign.get("id"),
                                campaign.get("title"),
                                campaign.get("total_funds"),
                                campaign.get("funds")
                        ));
                    } else {
                        break;
                    }
                }

                MainActivityCampaignAdapter campaignAdapter = new MainActivityCampaignAdapter(campaignList);
                campaignRecyclerView.setAdapter(campaignAdapter);
                campaignAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void populateNotifications(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("commuities/sample_community/notifications");

        notificationRecyclerView = (RecyclerView) findViewById(R.id.activity_home_notice_recycler);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
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

                MainActivityNotificationAdapter notificationAdapter = new MainActivityNotificationAdapter(notificationList);
                notificationRecyclerView.setAdapter(notificationAdapter);
                notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void populateComplaints(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        
        DatabaseReference ref = database.getReference("commuities/sample_community/complaints");

        complaintsRecyclerView = (RecyclerView) findViewById(R.id.activity_home_complaints_recycler);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        complaintsRecyclerView.setLayoutManager(eventLayoutManager);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Map> post = (ArrayList<Map>) dataSnapshot.getValue();
                List<ComplaintsItem> complaintsList = new ArrayList<ComplaintsItem>();

                int complaintsCount = 0;
                for (Map<String, String> complaints : post){
                    if (complaintsCount < 5) {
                        complaintsList.add(new ComplaintsItem(
                                complaints.get("by_user"),
                                complaints.get("description"),
                                complaints.get("time"),
                                complaints.get("title")
                        ));
                    } else {
                        break;
                    }
                }

                MainActivityComplaintsAdapter notificationAdapter = new MainActivityComplaintsAdapter(complaintsList);
                complaintsRecyclerView.setAdapter(notificationAdapter);
                notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        Toast.makeText(this, "Fetching data from server", Toast.LENGTH_LONG).show();
    }

}
