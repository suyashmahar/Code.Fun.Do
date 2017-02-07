package com.android.example.neighbours;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Harshit Bansal on 2/6/2017.
 */

public class StaffDbOperations extends SQLiteOpenHelper {

    public static final int DB_VERSION=1;
    public static final String DB_NAME="member.info.db";
    public static final String CREATE_STAFF_QUERY="create table "+FlatStaff.StaffEntry.TABLE_NAME+
            "("+FlatStaff.StaffEntry.NAME+ " text,"+ FlatStaff.StaffEntry.PHONE+ " text,"+
            FlatStaff.StaffEntry.BADGE+" text,"+FlatStaff.StaffEntry.ADDRESS+" text);";
    StaffDbOperations(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
        Log.d("StaffDatabase","Database Created..");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STAFF_QUERY);
        Log.d("StaffDatabase","Table Created..");
    }

    public void addInformation(SQLiteDatabase db,String name,String phone,String badge,String address){
        ContentValues contentValues=new ContentValues();
        contentValues.put(FlatStaff.StaffEntry.NAME,name);
        contentValues.put(FlatStaff.StaffEntry.PHONE,phone);
        contentValues.put(FlatStaff.StaffEntry.BADGE,badge);
        contentValues.put(FlatStaff.StaffEntry.ADDRESS,address);
        db.insert(FlatStaff.StaffEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations","One row inserted..");
    }

    public Cursor getInformation(SQLiteDatabase db){
        String[] projections={FlatStaff.StaffEntry.NAME,FlatStaff.StaffEntry.PHONE,
                FlatStaff.StaffEntry.BADGE,FlatStaff.StaffEntry.ADDRESS};

        Cursor cursor=db.query(FlatStaff.StaffEntry.TABLE_NAME,projections,
                null,null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
