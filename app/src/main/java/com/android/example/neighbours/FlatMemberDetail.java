package com.android.example.neighbours;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FlatMemberDetail extends AppCompatActivity {
    TextView name,relation,mobile,email;
    LinearLayout phoneCall,emailSend;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_contact);
        name=(TextView)findViewById(R.id.txt_username);
        relation=(TextView)findViewById(R.id.contact_relation);
        mobile=(TextView)findViewById(R.id.contact_mobile);
        email=(TextView)findViewById(R.id.contact_email);

        phoneCall=(LinearLayout)findViewById(R.id.make_phone_call);
        emailSend=(LinearLayout)findViewById(R.id.send_email);

        phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel: "+number));
                startActivity(intent);
            }
        });

        emailSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            name.setText(extras.getString("name"));
            relation.setText(extras.getString("relation"));
            mobile.setText(extras.getString("mobile"));
            email.setText(extras.getString("email"));
            number=mobile.getText().toString();
        }
    }
}