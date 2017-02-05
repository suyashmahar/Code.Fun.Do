package com.android.example.neighbours;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import java.util.Calendar;
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

public class CreateEvent extends AppCompatActivity implements View.OnClickListener{

    EditText eventName,eventVenue,eventDescription,eventGuests;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private static final int RESULT_LOAD_IMAGE=1;

    EditText date,time;
    ImageView selectTime,selectDate;
    LinearLayout attachPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventName=(EditText)findViewById(R.id.create_event_title);
        eventVenue=(EditText)findViewById(R.id.create_event_venue);
        eventGuests=(EditText)findViewById(R.id.create_event_guests);
        eventDescription=(EditText)findViewById(R.id.create_event_description);

        date=(EditText) findViewById(R.id.create_event_date_display);
        time=(EditText) findViewById(R.id.create_event_time_display);
        selectTime=(ImageView)findViewById(R.id.create_event_select_time);
        selectDate=(ImageView)findViewById(R.id.create_event_select_date);

        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);

        attachPhoto=(LinearLayout)findViewById(R.id.layout_event_attach_photo);

        attachPhoto=(LinearLayout)findViewById(R.id.layout_event_attach_photo);
        attachPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        final String desc = eventDescription.toString();
        final String title = eventName.getText().toString();

        Button okButton = (Button) findViewById(R.id.create_event_post);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uploader uploader = new Uploader(getApplicationContext());
                uploader.createAndPushEvent(new Events(desc, "a1", "sample_image", "comm","sample", "12:00", title, "100k"));
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

            ImageView imageView = (ImageView) findViewById(R.id.event_image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == selectDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


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
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

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



