package com.android.example.neighbours;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyFlatMembers extends AppCompatActivity {

    private FloatingActionButton addMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flat_members);

        addMember=(FloatingActionButton)findViewById(R.id.flat_member_add);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyFlatMembers.this,AddFlatMember.class);
                startActivity(i);
            }
        });
    }
}
