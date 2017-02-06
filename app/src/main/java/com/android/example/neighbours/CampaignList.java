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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CampaignList extends AppCompatActivity implements ClickListener{
    RecyclerView campaignRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent newIntent = new Intent(view.getContext(), CreateCampaign.class);
                startActivity(newIntent);
            }
        });

        populateCampaigns();
    }


    public void populateCampaigns(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("commuities/sample_community/campaigns");

        campaignRecyclerView = (RecyclerView) findViewById(R.id.campaign_list_recycler);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
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

               CampaignListAdapter campaignAdapter = new CampaignListAdapter(campaignList);
                campaignRecyclerView.setAdapter(campaignAdapter);
                campaignAdapter.setClickListener(CampaignList.this);
                campaignAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {
        ImageView img=(ImageView)view.findViewById(R.id.campaign_tile_image);
        TextView title=(TextView)view.findViewById(R.id.campaign_tile_title);
        TextView description=(TextView)view.findViewById(R.id.campaign_tile_description);
        TextView date=(TextView)view.findViewById(R.id.campaign_tile_rel_time);
        TextView hearts=(TextView)view.findViewById(R.id.campaign_tile_total_fund);

        Intent i=new Intent(CampaignList.this,EventDetail.class);

        i.putExtra("CampaignName",title.getText().toString());
        i.putExtra("CampaignDescription",description.getText().toString());
        i.putExtra("CampaignTime",date.getText().toString());
        i.putExtra("CampaignFund",hearts.getText().toString());
        startActivity(i);
    }
}
