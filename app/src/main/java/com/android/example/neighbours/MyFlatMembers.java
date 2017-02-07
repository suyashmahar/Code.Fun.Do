package com.android.example.neighbours;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MyFlatMembers extends AppCompatActivity {
    TextView name,relation,email,mobile;
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

        memberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                name=(TextView)view.findViewById(R.id.flat_member_name);
                relation=(TextView)view.findViewById(R.id.flat_member_relation);
                mobile=(TextView)view.findViewById(R.id.flat_member_mobile);
                email=(TextView)view.findViewById(R.id.flat_member_email);
                if(name==null) name.setText(" ");
                if(relation==null) relation.setText(" ");
                if(mobile==null) mobile.setText(" ");
                if(email==null) email.setText(" ");

                Intent i=new Intent(MyFlatMembers.this,FlatMemberDetail.class);
                i.putExtra("name",name.getText().toString());
                i.putExtra("relation",relation.getText().toString());
                i.putExtra("email",email.getText().toString());
                i.putExtra("mobile",mobile.getText().toString());

                startActivity(i);
            }
        });
    }
}