package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class CreateComplaint extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

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

        TextView currentDate = (TextView) findViewById(R.id.date_complaint);
        final String abc = DateFormat.getDateTimeInstance().format(new Date());
        String temp="";
        int i;

        for(i=0;abc.charAt(i)!=' ';++i)
        {
            temp+=abc.charAt(i);
        }

        /*
        int j=i;

        for( ;j<abc.length();++j)
        {
            temp+=abc.charAt(j);
        }
          */

        final String x=temp;

        currentDate.setText(x);

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
                Complaint complaint=new Complaint("sample_user",details.getText().toString(),x,name.getText().toString());
                uploader.createAndPushComplaints(complaint);
                Toast.makeText(getApplicationContext(), "Complaint sent", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
