package com.android.example.neighbours;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Harshit Bansal on 2/6/2017.
 */

public class BackgroundTaskStaff extends AsyncTask<String,Staff,String> {
    Context ctx;
    StaffAdapter staffAdapter;
    Activity activity;
    ListView listView;
    BackgroundTaskStaff(Context ctx){

        this.ctx=ctx;
        activity=(Activity)ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String method=params[0];

       StaffDbOperations staffDbOperations=new StaffDbOperations(ctx);

        if(method.equals("add_staff_info")){
            String name=params[1];
            String phone=params[2];
            String badge=params[3];
            String address=params[4];
            SQLiteDatabase db=staffDbOperations.getWritableDatabase();
            staffDbOperations.addInformation(db,name,phone,badge,address);
            return "One staff member added...";

        }

        else if(method.equals("get_staff_info")){
            listView=(ListView)activity.findViewById(R.id.flat_staff_list);
            SQLiteDatabase db=staffDbOperations.getReadableDatabase();
            Cursor cursor=staffDbOperations.getInformation(db);
            staffAdapter=new StaffAdapter(ctx,R.layout.flat_staff_tile);
            String name,phone,badge,address;
            while(cursor.moveToNext()){
                name=cursor.getString(cursor.getColumnIndex(FlatStaff.StaffEntry.NAME));
                phone=cursor.getString(cursor.getColumnIndex(FlatStaff.StaffEntry.PHONE));
                badge=cursor.getString(cursor.getColumnIndex(FlatStaff.StaffEntry.BADGE));
                address=cursor.getString(cursor.getColumnIndex(FlatStaff.StaffEntry.ADDRESS));
                Staff staff=new Staff(name,phone,badge,address);
                publishProgress(staff);

            }

            return "get_staff_info";
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Staff... values) {
        staffAdapter.add(values[0]);


    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("get_staff_info")) {
            listView.setAdapter(staffAdapter);
        } else {

            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
    }
}
