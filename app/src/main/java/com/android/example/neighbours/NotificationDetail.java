// NotificationTitle,NotificationTime,NotificationDescription
package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationDetail extends AppCompatActivity {
    TextView notificationDetailTitle,notificationDetailTime,notificationDetailDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        notificationDetailTitle=(TextView)findViewById(R.id.notification_detail_title);
        notificationDetailTime=(TextView)findViewById(R.id.notification_detail_time);
        notificationDetailDescription=(TextView)findViewById(R.id.notification_detail_description);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String notificationTitle=extras.getString("NotificationTitle");
            String notificationTime=extras.getString("NotificationTime");
            String notificationDescription=extras.getString("NotificationDescription");

            notificationDetailTitle.setText(notificationTitle);
            notificationDetailTime.setText(notificationTime);
            notificationDetailDescription.setText(notificationDescription);

        }
    }
}
