package com.android.example.neighbours;

import java.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class CreateComplaint extends AppCompatActivity
{
    String[] words;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

        final RadioButton left=(RadioButton) findViewById(R.id.radio_pub);
        final RadioButton right=(RadioButton) findViewById(R.id.radio_private);
        TextView date=(TextView)findViewById(R.id.date_complaint);


        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        words=currentDateTimeString.split("\\s");

// textView is the TextView view that should display it
        date.setText(currentDateTimeString);

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
            public void onClick(View view)
            {

                EditText details=(EditText) findViewById(R.id.message_complaint);
                EditText name=(EditText) findViewById(R.id.complaint_name);

                Uploader uploader = new Uploader(getApplicationContext());
                //uploader.createAndPushNotification(new NotificationItem(detailsTextView.getText().toString(), "12:00", titleTextView.getText().toString()));

                //uploader.createAndPushComplaints(new Complaint());
                Complaint complaint=new Complaint("sample_user",details.getText().toString(),words[0]+"\n"+words[1],name.getText().toString());
                uploader.createAndPushComplaints(complaint);
                Toast.makeText(getApplicationContext(), "Complaint sent", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
