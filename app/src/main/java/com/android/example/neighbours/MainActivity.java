//launch login page of activity and links to CreateNewAccount activity
package com.android.example.neighbours;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    Boolean bool=false;
    EditText username,password;
    Button loginButton;
    Button createNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView hide=(ImageView) findViewById(R.id.hide_show);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        loginButton=(Button)findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.toString() == "codefundo" && password.toString() == "password") {
                    Intent i=new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(i);

                }
            }
        });

        findViewById(R.id.username).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.btn_login).requestFocusFromTouch();
                findViewById(R.id.btn_create_new).requestFocusFromTouch();
            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bool){
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    bool=true;
                }
                else{
                    bool=false;
                    password.setInputType(129);
                }
            }
        });

        createNewAccount=(Button)findViewById(R.id.btn_create_new);
        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,CreateNewAccount.class);
                startActivity(i);
            }
        });

    }


}

