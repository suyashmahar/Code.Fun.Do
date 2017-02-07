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

public class MemberDbOperations extends SQLiteOpenHelper {

    public static final int DB_VERSION=1;
    public static final String DB_NAME="member.info.db";
    public static final String CREATE_MEMBER_QUERY="create table "+FlatMember.MemberEntry.TABLE_NAME+
            "("+FlatMember.MemberEntry.NAME+ " text,"+ FlatMember.MemberEntry.PHONE+ " text,"+
            FlatMember.MemberEntry.EMAIL+" text,"+FlatMember.MemberEntry.RELATION+" text);";
    public static final String CREATE_STAFF_QUERY="create table "+FlatStaff.StaffEntry.TABLE_NAME+
            "("+FlatStaff.StaffEntry.NAME+ " text,"+ FlatStaff.StaffEntry.PHONE+ " text,"+
            FlatStaff.StaffEntry.BADGE+" text,"+FlatStaff.StaffEntry.ADDRESS+" text);";
    MemberDbOperations(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
        Log.d("MemberDatabase","Database Created..");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEMBER_QUERY);
        db.execSQL(CREATE_STAFF_QUERY);
        Log.d("MemberDatabase","Table Created..");
    }

    public void addInformation(SQLiteDatabase db,String name,String phone,String email,String relation){
        ContentValues contentValues=new ContentValues();
        contentValues.put(FlatMember.MemberEntry.NAME,name);
        contentValues.put(FlatMember.MemberEntry.PHONE,phone);
        contentValues.put(FlatMember.MemberEntry.EMAIL,email);
        contentValues.put(FlatMember.MemberEntry.RELATION,relation);
        db.insert(FlatMember.MemberEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations","One row inserted..");
    }

    public Cursor getInformation(SQLiteDatabase db){
        String[] projections={FlatMember.MemberEntry.NAME,FlatMember.MemberEntry.PHONE,
                FlatMember.MemberEntry.EMAIL,FlatMember.MemberEntry.RELATION};

        Cursor cursor=db.query(FlatMember.MemberEntry.TABLE_NAME,projections,
                null,null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
