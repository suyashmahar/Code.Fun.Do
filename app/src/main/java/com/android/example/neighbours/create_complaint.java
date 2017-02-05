package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class create_complaint extends AppCompatActivity
{
    EditText complaintName,complaintDescription;
    Button postComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

        complaintName=(EditText)findViewById(R.id.complaint_name);
        complaintDescription=(EditText)findViewById(R.id.message_complaint);
        postComplaint=(Button)findViewById(R.id.send);

        postComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uploader uploader = new Uploader(getApplicationContext());
                Events eventToAdd = new Events(complaintDescription.toString(), "a1", "sample_image", "sample_community", "sample_organizer", "12:00",complaintName.toString(),"100k");
                /*
                final DatabaseReference ref = database.getReference("commuities/sample_community/events/" + campaignCount);
                Events eventToAdd = new Events("this is a description", "a1", "sample_image", "sample_community", "sample_organizer", "12:00", "sample_title","100k");
                ref.setValue(eventToAdd);
                campaignCount++;
                */
                uploader.createAndPushEvent(eventToAdd);

            }
        });

        final RadioButton left=(RadioButton) findViewById(R.id.radio_pub);
        final RadioButton right=(RadioButton) findViewById(R.id.radio_private);

        left.setChecked(true);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(left.isChecked()==true)
                {
                    left.setChecked(false);
                }
                right.setChecked(true);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(right.isChecked()==true)
                    right.setChecked(false);

                left.setChecked(true);
            }
        });

        Button confirm=(Button) findViewById(R.id.send);

        //method to confirm complaint
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
