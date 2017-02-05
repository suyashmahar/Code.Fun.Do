package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MyFlat extends AppCompatActivity {

    Button flatMembers,flatVehivles,flatStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flat);
        flatMembers=(Button)findViewById(R.id.myflat_members_btn);
        flatVehivles=(Button)findViewById(R.id.myflat_vehicles_btn);
        flatStaff=(Button)findViewById(R.id.myflat_staff_btn);


    }
}
