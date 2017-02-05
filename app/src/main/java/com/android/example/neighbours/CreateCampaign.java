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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateCampaign extends AppCompatActivity {
    LinearLayout attachPhoto;
    TextView date,time;
    ImageView selectTime;
    private int pHour;
    private int pMinute;
    private static int RESULT_LOAD_IMAGE = 1;
    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
    static final int TIME_DIALOG_ID = 0;

    /** Callback received when the user "picks" a time in the dialog */
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pHour = hourOfDay;
                    pMinute = minute;
                    updateDisplay();
                }
            };

    private void updateDisplay() {
        time.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)));
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign);
        date=(TextView)findViewById(R.id.create_campaign_date_display);
        time=(TextView)findViewById(R.id.create_campaign_time_display);
        selectTime=(ImageView)findViewById(R.id.create_campaign_select_time);
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
                Calendar cal = Calendar.getInstance();
                pHour = cal.get(Calendar.HOUR_OF_DAY);
                pMinute = cal.get(Calendar.MINUTE);

                /** Display the current time in the TextView */
                updateDisplay();
            }
        });
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
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, false);
        }
        return null;
    }
    public void selectDate(View view){
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker = new DatePickerDialog(CreateCampaign.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String dated = "Date"+String.valueOf(year) +"-"+String.valueOf(monthOfYear)
                        +"-"+String.valueOf(dayOfMonth);
                date.setText(dated);
            }
        }, yy, mm, dd);
        datePicker.show();
    }
}

