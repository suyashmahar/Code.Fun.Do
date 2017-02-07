package com.android.example.neighbours;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class HomeActivity extends AppCompatActivity implements ClickListener{
    RecyclerView eventsRecyclerView;
    RecyclerView campaignRecyclerView;
    RecyclerView notificationRecyclerView;
    RecyclerView complaintsRecyclerView;

    ProgressBar eventsProgressSpinner,campaignProgressSpinner,
            notificationProgressSpinner,complaintsProgressSpinner;

    int campaignCount = 1;
    private ClickListener clickListener=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Uploader uploader = new Uploader(this);

        eventsProgressSpinner = (ProgressBar) findViewById(R.id.activity_home_events_load_progress);
        campaignProgressSpinner = (ProgressBar) findViewById(R.id.activity_home_campaign_load_progress);
        notificationProgressSpinner = (ProgressBar) findViewById(R.id.activity_home_notice_load_progress);
        complaintsProgressSpinner = (ProgressBar) findViewById(R.id.activity_home_complaints_load_progress);

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

            }
        });
        Button moreCampaignsButton = (Button)findViewById(R.id.activity_home_more_campaign_button);
        moreCampaignsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(view.getContext(), CampaignList.class );
                startActivity(newIntent);

            }
        });
        Button moreNotificationButton = (Button)findViewById(R.id.activity_home_more_notice_button);
        moreNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(view.getContext(), NotificationList.class );
                startActivity(newIntent);

            }
        });
        Button moreNoticesButton =(Button)findViewById(R.id.activity_home_more_notice_button);
        moreNoticesButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     //Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_LONG).show();
                                                     Intent newIntent = new Intent(v.getContext(), NotificationList.class);
                                                     startActivity(newIntent);
                                                 }
                                             });
        Button moreCoplaintButton=(Button)findViewById(R.id.activity_home_more_complaints_button);
        moreCoplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),ComplaintList.class);
                startActivity(i);
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
                eventAdapter.setClickListener(HomeActivity.this);
                eventAdapter.notifyDataSetChanged();
                eventsProgressSpinner.setVisibility(View.GONE);
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
                campaignAdapter.setClickListener(HomeActivity.this);
                campaignAdapter.notifyDataSetChanged();
                campaignProgressSpinner.setVisibility(View.GONE);

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
                notificationAdapter.setClickListener(HomeActivity.this);
                notificationAdapter.notifyDataSetChanged();
                notificationProgressSpinner.setVisibility(View.GONE);

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
                complaintsProgressSpinner.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        Toast.makeText(this, "Fetching data from server", Toast.LENGTH_LONG).show();
    }


    @Override
    public void itemClicked(View view, int position) {
        if(view == notificationRecyclerView){
            TextView title=(TextView)view.findViewById(R.id.notification_card_title);
            TextView date=(TextView)view.findViewById(R.id.notification_card_date);
            TextView description=(TextView)view.findViewById(R.id.notification_card_description);
            Intent i=new Intent(HomeActivity.this,NotificationDetail.class);

            i.putExtra("NotificationTitle",title.getText().toString());
            i.putExtra("NotificationDescription",description.getText().toString());
            i.putExtra("NotificationTime",date.getText().toString());

            startActivity(i);
        }
        if(view==eventsRecyclerView){
            ImageView img=(ImageView)view.findViewById(R.id.event_card_image);
            TextView title=(TextView)view.findViewById(R.id.event_card_title);
            TextView description=(TextView)view.findViewById(R.id.event_card_description);
            TextView date=(TextView)view.findViewById(R.id.event_card_rel_time);
            TextView hearts=(TextView)view.findViewById(R.id.event_card_hearts);
            TextView comments=(TextView)view.findViewById(R.id.event_card_comments_count);

            Intent i=new Intent(HomeActivity.this,EventDetail.class);

            i.putExtra("EventName",title.getText().toString());
            i.putExtra("EventDescription",description.getText().toString());
            i.putExtra("EventTime",date.getText().toString());
            i.putExtra("EventHearts",hearts.getText().toString());
            i.putExtra("EventComments",comments.getText().toString());
            startActivity(i);
        }
        if(view==campaignRecyclerView){
            ImageView img=(ImageView)view.findViewById(R.id.campaign_tile_image);
            TextView title=(TextView)view.findViewById(R.id.campaign_tile_title);
            TextView description=(TextView)view.findViewById(R.id.campaign_tile_description);
            TextView date=(TextView)view.findViewById(R.id.campaign_tile_rel_time);
            TextView hearts=(TextView)view.findViewById(R.id.campaign_tile_total_fund);

            Intent i=new Intent(HomeActivity.this,CampaignDetail.class);

            i.putExtra("CampaignName",title.getText().toString());
            i.putExtra("CampaignDescription",description.getText().toString());
            i.putExtra("CampaignTime",date.getText().toString());
            i.putExtra("CampaignFund",hearts.getText().toString());
            startActivity(i);

        }
    }
}
