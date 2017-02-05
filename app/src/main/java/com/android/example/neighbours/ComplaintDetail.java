//ComplaintTitle,ComplaintTime,ComplaintSender,ComplaintDescription

package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.android.example.neighbours.ImageTo64BitString.convertToBase64;

public class ComplaintDetail extends AppCompatActivity {

    TextView complaintDetailTitle,complaintDetailTime,complaintDetailSender,complaintDetailDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_detail);

        complaintDetailTitle=(TextView)findViewById(R.id.complaint_detail_title);
        complaintDetailTime=(TextView)findViewById(R.id.complaint_detail_time);
        complaintDetailSender=(TextView)findViewById(R.id.complaint_detail_sender);
        complaintDetailDescription=(TextView)findViewById(R.id.complaint_detail_description);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String complaintTitle=extras.getString("ComplaintTitle");
            String complaintSender=extras.getString("ComplaintSender");
            String complaintTime=extras.getString("ComplaintTime");
            String complaintDescription=extras.getString("ComplaintDescription");



            complaintDetailTitle.setText(complaintTitle);
            complaintDetailSender.setText(complaintSender);
            complaintDetailTime.setText(complaintTime);
            complaintDetailDescription.setText(complaintDescription);

        }
    }
}
