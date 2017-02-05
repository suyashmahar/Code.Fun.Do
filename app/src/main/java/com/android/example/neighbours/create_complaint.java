package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class create_complaint extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

        final RadioButton left=(RadioButton) findViewById(R.id.radio_pub);
        final RadioButton right=(RadioButton) findViewById(R.id.radio_private);

        left.setChecked(true);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(left.isChecked()==true)
                {
                    left.setChecked(false);
                }
                right.setChecked(true);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(right.isChecked()==true)
                    right.setChecked(false);

                left.setChecked(true);
            }
        });

        Button confirm=(Button) findViewById(R.id.send);

        //method to confirm complaint
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
