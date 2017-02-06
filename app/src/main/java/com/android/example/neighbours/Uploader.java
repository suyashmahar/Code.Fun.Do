package com.android.example.neighbours;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Suyash on 05-02-2017.
 */

public class Uploader {
    public static final String PREFERENCE_NAME = "com.android.example.neighbours.details";
    static SharedPreferences settings;

    public static final String EVENTS = "events";
    public static final String CAMPAIGNS = "campaign";
    public static final String COMPLAINTS = "complaints";
    public static final String NOTIFICATIONS = "notifications";

    Context context;

    public static int eventCount;
    public static int campaignCount;
    public static int complaintsCount;
    public static int notificationCount;

    public static String community;

    public Uploader(Context context) {
        this.context = context;
        settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        community = settings.getString("community_name", "sample_community");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        final DatabaseReference eventsCountRef = database.getReference("commuities/" + community + "/counts");

        eventsCountRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map post = (Map) dataSnapshot.getValue();

                eventCount = (int)(long)(post.get(EVENTS));
                campaignCount = (int)(long)(post.get(CAMPAIGNS));
                notificationCount = (int)(long)post.get(NOTIFICATIONS);
                complaintsCount = (int)(long)post.get(COMPLAINTS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //
            }
        });


        //commuities/sample_community/counts/campaign

        final DatabaseReference ref = database.getReference("commuities/sample_community/events/" + campaignCount);
        Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");
        ref.setValue(eventToAdd);
    }

    public int createAndPushEvent(Events event){
        settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        String community = settings.getString("community_name", "sample_community");


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("commuities/" + community + "/events/" + (int)(eventCount + 1));
        ref.setValue(event);
        incrementCount(EVENTS, 1);
        eventCount++;
        return 1;
    }

    public int createAndPushCampaign(Campaign campaign){
        settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        String community = settings.getString("community_name", "sample_community");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("commuities/" + community + "/campaigns/" + (int)(campaignCount + 1));
        ref.setValue(campaign);
        incrementCount(CAMPAIGNS, 1);
        campaignCount++;
        return 1;
    }

    public int createAndPushNotification(NotificationItem notification){
        settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        String community = settings.getString("community_name", "sample_community");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("commuities/" + community + "/notifications/" + (int)(notificationCount + 1));
        ref.setValue(notification);
        incrementCount(NOTIFICATIONS, 1);
        notificationCount++;
        return 1;
    }


    public int createAndPushComplaints(Complaint complaint){
        settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        String community = settings.getString("community_name", "sample_community");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("commuities/" + community + "/complaints/" + (int)(complaintsCount + 1));
        ref.setValue(complaint);
        incrementCount(COMPLAINTS, 1);
        complaintsCount++;
        return 1;
    }

    public static void incrementCount(String countType, int incrementBy){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");
        campaignCount++;

        DatabaseReference ref;

        switch (countType){
            case (NOTIFICATIONS):
                ref = database.getReference("commuities/" + community + "/counts/" + countType);
                ref.setValue(notificationCount + incrementBy);
                notificationCount += incrementBy; // Though this would be updated ASA server receives data
                break;
            case (EVENTS):
                ref = database.getReference("commuities/" + community + "/counts/" + countType);
                ref.setValue(eventCount + incrementBy);
                eventCount += incrementBy; // Though this would be updated ASA server receives data
                break;
            case (CAMPAIGNS):
                ref = database.getReference("commuities/" + community + "/counts/" + countType);
                ref.setValue(campaignCount + incrementBy);
                campaignCount += incrementBy; // Though this would be updated ASA server receives data
                break;
            case (COMPLAINTS):
                ref = database.getReference("commuities/" + community + "/counts/" + countType);
                ref.setValue(complaintsCount + incrementBy);
                complaintsCount += incrementBy; // Though this would be updated ASA server receives data
                break;
            default:
                Log.e("Error", "Invalid countType in Uploader::UpdateCount()");
        }
    }
}
