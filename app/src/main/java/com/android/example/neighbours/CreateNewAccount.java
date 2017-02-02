//link this class to CreateNewApartment class in case the person wants to create his own locality
package com.android.example.neighbours;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Harshit Bansal on 2/1/2017.
 */

public class CreateNewAccount extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
    }
    public void createApartment(View view){
        Intent i=new Intent(this,CreateNewApartment.class);
        startActivity(i);
    }
}
