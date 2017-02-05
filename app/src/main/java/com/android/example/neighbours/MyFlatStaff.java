package com.android.example.neighbours;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyFlatStaff extends AppCompatActivity {

    private FloatingActionButton addStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flat_staff);
        addStaff=(FloatingActionButton)findViewById(R.id.flat_staff_add);
        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyFlatStaff.this,AddFlatStaff.class);
                startActivity(i);
            }
        });
    }
}
