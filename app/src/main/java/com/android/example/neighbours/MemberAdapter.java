package com.android.example.neighbours;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit Bansal on 2/6/2017.
 */

public class MemberAdapter extends ArrayAdapter {
    List memberList=new ArrayList();
    public MemberAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Member object) {
        memberList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int position) {
        return memberList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        MemberHolder memberHolder;
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.flat_member_tile,parent,false);
            memberHolder=new MemberHolder();
            memberHolder.txt_member_name=(TextView)row.findViewById(R.id.flat_member_name);
            memberHolder.txt_member_phone=(TextView)row.findViewById(R.id.flat_member_mobile);
            memberHolder.txt_member_email=(TextView)row.findViewById(R.id.flat_member_email);
            memberHolder.txt_member_relation=(TextView)row.findViewById(R.id.flat_member_relation);
            row.setTag(memberHolder);
        }

        else{
            memberHolder=(MemberHolder)row.getTag();
        }

        Member member=(Member)getItem(position);
        memberHolder.txt_member_name.setText(member.getName().toString());
        memberHolder.txt_member_phone.setText(member.getPhone().toString());
        memberHolder.txt_member_email.setText(member.getEmail().toString());
        memberHolder.txt_member_relation.setText(member.getRelation().toString());

        return row;
    }
    static class MemberHolder{
        TextView txt_member_name,txt_member_phone,txt_member_email,txt_member_relation;

    }
}
