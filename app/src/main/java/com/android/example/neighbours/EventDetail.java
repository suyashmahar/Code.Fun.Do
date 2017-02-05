//  EventImage,EventName,EventHearts,EventComments,EventTime,EventDescription
package com.android.example.neighbours;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.android.example.neighbours.ImageTo64BitString.convertToBase64;

public class EventDetail extends AppCompatActivity {
    ImageView eventDetailImage;
    TextView eventDetailEventName,eventDetailHearts,eventDetailComments,eventDetailTime,eventDetailDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        eventDetailImage=(ImageView)findViewById(R.id.activity_event_detail_cover_image);
        eventDetailEventName=(TextView)findViewById(R.id.event_detail_name);
        eventDetailHearts=(TextView)findViewById(R.id.event_detail_hearts);
        eventDetailComments=(TextView)findViewById(R.id.event_detail_comments_count);
        eventDetailTime=(TextView)findViewById(R.id.event_detail_rel_time);
        eventDetailDescription=(TextView)findViewById(R.id.event_detail_description);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String image=extras.getString("EventImage");
            String eventName=extras.getString("EventName");
            String eventHearts=extras.getString("EventHearts");
            String eventComments=extras.getString("EventComments");
            String eventTime=extras.getString("EventTime");
            String eventDescription=extras.getString("EventDescription");
            String encodedImage;
            try{
                encodedImage=convertToBase64(image);
                eventDetailImage.setImageResource(Integer.parseInt(encodedImage));
            }
            catch (Exception e){

            }

            eventDetailEventName.setText(eventName);
            eventDetailHearts.setText(eventHearts);
            eventDetailComments.setText(eventComments);
            eventDetailTime.setText(eventTime);
            eventDetailDescription.setText(eventDescription);

        }

    }
}
