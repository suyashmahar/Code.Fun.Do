package com.android.example.neighbours;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyFlatVehicles extends AppCompatActivity {

    private FloatingActionButton addVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flat_vehicles);

        addVehicle=(FloatingActionButton)findViewById(R.id.flat_vehicle_add);
        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent i=new Intent(MyFlatVehicles.this,AddFlatVehicle.class);
                startActivity(i);
            }
        });


    }
}
