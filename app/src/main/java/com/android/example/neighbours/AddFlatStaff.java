package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddFlatStaff extends AppCompatActivity {

    EditText staffName,staffPhone,staffBadge,staffAddress;
    String staffname,staffphone,staffbadge,staffaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat_staff);

        staffName=(EditText)findViewById(R.id.add_staff_name);
        staffPhone=(EditText)findViewById(R.id.add_staff_mobile);
        staffBadge=(EditText)findViewById(R.id.add_staff_badge);
        staffAddress=(EditText)findViewById(R.id.add_staff_address);
    }

    public void saveStaff(View view){
        staffname=staffName.getText().toString();
        staffphone=staffPhone.getText().toString();
        staffbadge=staffBadge.getText().toString();
        staffaddress=staffAddress.getText().toString();
        BackgroundTaskStaff backgroundTask=new BackgroundTaskStaff(this);
        backgroundTask.execute("add_staff_info",staffname,staffphone,staffbadge,staffaddress);
        finish();
    }
}
