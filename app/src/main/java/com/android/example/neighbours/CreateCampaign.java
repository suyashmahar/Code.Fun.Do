//
package com.android.example.neighbours;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateCampaign extends AppCompatActivity implements View.OnClickListener{

    EditText campaignName,campaignVenue,campaignDescription,campaignGuests, campaignGoals;

    private int mYear, mMonth, mDay, mHour, mMinute;

    LinearLayout attachPhoto;

    EditText date,time;

    ImageView selectTime,selectDate;


    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign);

        ImageView imageView=(ImageView) findViewById(R.id.campaign_image);

        campaignName=(EditText)findViewById(R.id.create_campaign_title);
        campaignVenue=(EditText)findViewById(R.id.create_campaign_venue);
        campaignGuests=(EditText)findViewById(R.id.create_campaign_guests);
        campaignDescription=(EditText)findViewById(R.id.create_campaign_description);
        campaignGoals =(EditText)findViewById(R.id.create_campaign_add_goal);

        date=(EditText) findViewById(R.id.create_campaign_date_display);
        time=(EditText) findViewById(R.id.create_campaign_time_display);
        selectDate=(ImageView)findViewById(R.id.create_campaign_select_date);
        selectTime=(ImageView)findViewById(R.id.create_campaign_select_time);

        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);

        attachPhoto=(LinearLayout)findViewById(R.id.layout_campaign_attach_photo);
        attachPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        //putting data in database
        Button saving=(Button) findViewById(R.id.create_campaign_post);

        saving.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String latest_time=date.getText().toString()+"\n"+time.getText().toString();
                 Uploader uploader=new Uploader((getApplicationContext()));
                //Events events=new Events(campaignDescription.toString(),"a1","sample_image","sample_community","sample_user",latest

                Campaign campaign = new Campaign(campaignDescription.getText().toString(),"sample funds","sample id",campaignName.getText().toString(),campaignGoals.getText().toString(), "10",latest_time);
                uploader.createAndPushCampaign(campaign);
                Toast.makeText(getApplicationContext(), "Campaign created", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.campaign_image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == selectDate) {

            // Get Current Date
            final java.util.Calendar c = java.util.Calendar.getInstance();
            mYear = c.get(java.util.Calendar.YEAR);
            mMonth = c.get(java.util.Calendar.MONTH);
            mDay = c.get(java.util.Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == selectTime) {

            // Get Current Time
            final java.util.Calendar c = java.util.Calendar.getInstance();
            mHour = c.get(java.util.Calendar.HOUR_OF_DAY);
            mMinute = c.get(java.util.Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}

