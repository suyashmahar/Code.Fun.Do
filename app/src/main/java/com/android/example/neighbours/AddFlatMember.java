package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddFlatMember extends AppCompatActivity {

    Button saveMember;
    EditText memberName,memberPhone,memberEmail,memberRelation;
    String membername,memberphone,memberemail,memberrelation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat_member);

        memberName=(EditText)findViewById(R.id.add_member_name);
        memberPhone=(EditText)findViewById(R.id.add_member_mobile);
        memberEmail=(EditText)findViewById(R.id.add_member_email);
        memberRelation=(EditText)findViewById(R.id.add_member_relation);




    }
    public void saveMember(View view){
        membername=memberName.getText().toString();
        memberphone=memberPhone.getText().toString();
        memberemail=memberEmail.getText().toString();
        memberrelation=memberRelation.getText().toString();
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute("add_member_info",membername,memberphone,memberemail,memberrelation);
        finish();
    }
}
