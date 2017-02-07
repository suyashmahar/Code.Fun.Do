package com.android.example.neighbours;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MyFlatMembers extends AppCompatActivity {

    private FloatingActionButton addMember;
    ListView memberListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flat_members);

        memberListView=(ListView)findViewById(R.id.flat_members_list);

        addMember=(FloatingActionButton)findViewById(R.id.flat_member_add);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyFlatMembers.this,AddFlatMember.class);
                startActivity(i);
            }
        });

        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute("get_member_info");

    }
}
