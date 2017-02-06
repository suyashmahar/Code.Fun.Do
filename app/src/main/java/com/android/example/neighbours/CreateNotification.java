package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNotification extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        Button save=(Button) findViewById(R.id.confirm);
        final TextView titleTextView = (TextView) findViewById(R.id.notify_name);
        final TextView detailsTextView = (TextView) findViewById(R.id.notify_message);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uploader uploader = new Uploader(getApplicationContext());
                uploader.createAndPushNotification(new NotificationItem(detailsTextView.getText().toString(), "12:00", titleTextView.getText().toString()));
                Toast.makeText(getApplicationContext(), "Notice sent", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
