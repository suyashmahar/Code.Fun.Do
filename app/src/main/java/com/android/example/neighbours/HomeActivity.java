package com.android.example.neighbours;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

public class HomeActivity extends AppCompatActivity {
    RecyclerView eventsRecyclerView;
    RecyclerView campaignRecyclerView;
    RecyclerView notificationRecyclerView;
    int campaignCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        populateEvents();
        populateCampaigns();
        populateNotifications();

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
        Button moreNoticesButton =(Button)findViewById(R.id.activity_home_more_notice_button);
       moreNoticesButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
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
                                campaign.get("total_funds")
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

}
