package com.android.example.neighbours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit Bansal on 2/7/2017.
 */

public class StaffAdapter extends ArrayAdapter {
        List staffList=new ArrayList();
public StaffAdapter(Context context, int resource) {
        super(context, resource);
        }

public void add(Staff object) {
        staffList.add(object);
        super.add(object);
        }

@Override
public int getCount() {
        return staffList.size();
        }

@Override
public Object getItem(int position) {
        return staffList.get(position);
        }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        StaffAdapter.StaffHolder staffHolder;
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.flat_staff_tile,parent,false);
            staffHolder=new StaffAdapter.StaffHolder();
            staffHolder.txt_staff_name=(TextView)row.findViewById(R.id.flat_staff_name);
            staffHolder.txt_staff_phone=(TextView)row.findViewById(R.id.flat_staff_mobile);
            staffHolder.txt_staff_badge=(TextView)row.findViewById(R.id.flat_staff_badge);
            staffHolder.txt_staff_address=(TextView)row.findViewById(R.id.flat_staff_address);
            row.setTag(staffHolder);
        }

        else{
            staffHolder=(StaffAdapter.StaffHolder)row.getTag();
        }

        Staff staff=(Staff)getItem(position);
        staffHolder.txt_staff_name.setText(staff.getName().toString());
        staffHolder.txt_staff_phone.setText(staff.getPhone().toString());
        staffHolder.txt_staff_badge.setText(staff.getBadge().toString());
        staffHolder.txt_staff_address.setText(staff.getAddress().toString());

        return row;
    }
    static class StaffHolder{
        TextView txt_staff_name,txt_staff_phone,txt_staff_badge,txt_staff_address;

    }


        }
