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

public class BackgroundTask extends AsyncTask<String,Member,String> {
    Context ctx;
    MemberAdapter memberAdapter;
    Activity activity;
    ListView listView;
    BackgroundTask(Context ctx){

        this.ctx=ctx;
        activity=(Activity)ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String method=params[0];

        MemberDbOperations memberDbOperations=new MemberDbOperations(ctx);

        if(method.equals("add_member_info")){
            String name=params[1];
            String phone=params[2];
            String email=params[3];
            String relation=params[4];
            SQLiteDatabase db=memberDbOperations.getWritableDatabase();
            memberDbOperations.addInformation(db,name,phone,email,relation);
            return "One row inserted...";

        }

        else if(method.equals("get_member_info")){
            listView=(ListView)activity.findViewById(R.id.flat_members_list);
            SQLiteDatabase db=memberDbOperations.getReadableDatabase();
            Cursor cursor=memberDbOperations.getInformation(db);
            memberAdapter=new MemberAdapter(ctx,R.layout.flat_member_tile);
            String name,phone,email,relation;
            while(cursor.moveToNext()){
                name=cursor.getString(cursor.getColumnIndex(FlatMember.MemberEntry.NAME));
                phone=cursor.getString(cursor.getColumnIndex(FlatMember.MemberEntry.PHONE));
                email=cursor.getString(cursor.getColumnIndex(FlatMember.MemberEntry.EMAIL));
                relation=cursor.getString(cursor.getColumnIndex(FlatMember.MemberEntry.RELATION));
                Member member=new Member(name,phone,email,relation);
                publishProgress(member);

            }

                return "get_member_info";
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Member... values) {
        memberAdapter.add(values[0]);


    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("get_member_info")) {
            listView.setAdapter(memberAdapter);
        } else {

            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
    }
}
