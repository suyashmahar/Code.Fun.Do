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

public class ComplaintList extends AppCompatActivity implements ClickListener{
    RecyclerView complaintRecyclerView;
    private ClickListener clickListener=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(view.getContext(), CreateComplaint.class);
                startActivity(createIntent);
            }
        });

        complaintRecyclerView = (RecyclerView) findViewById(R.id.complaint_list_recyclerview);

        populateComplaints();

    }


    public void populateComplaints(){
        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("commuities/sample_community/complaints");

        complaintRecyclerView = (RecyclerView) findViewById(R.id.complaint_list_recyclerview);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        complaintRecyclerView.setLayoutManager(eventLayoutManager);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Map> post = (ArrayList<Map>) dataSnapshot.getValue();
                List<ComplaintsItem> notificationList = new ArrayList<ComplaintsItem>();

                int complaintCount = 0;
                for (Map<String, String> complaint : post){
                    if (complaintCount < 5) {
                        notificationList.add(new ComplaintsItem(complaint.get("description"),
                                complaint.get("time"),
                                complaint.get("title"),complaint.get("by_user")
                        ));
                    } else {
                        break;
                    }
                }

                ComplaintListAdapter complaintAdapter = new ComplaintListAdapter(notificationList);
                complaintRecyclerView.setAdapter(complaintAdapter);
                complaintAdapter.setClickListener(ComplaintList.this);
                complaintAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {

        TextView title=(TextView)view.findViewById(R.id.complaint_tile_title);
        TextView description=(TextView)view.findViewById(R.id.complaint_tile_description);
        TextView sender=(TextView)view.findViewById(R.id.complaint_tile_sender);
        TextView date=(TextView)view.findViewById(R.id.complaint_tile_date);

        Intent i=new Intent(ComplaintList.this,ComplaintDetail.class);

        i.putExtra("ComplaintTitle",title.getText().toString());
        i.putExtra("ComplaintSender",sender.getText().toString());
        i.putExtra("ComplaintDescription",description.getText().toString());
        i.putExtra("ComplaintTime",date.getText().toString());

        startActivity(i);
    }
}
