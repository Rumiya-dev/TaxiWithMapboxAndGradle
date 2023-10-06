package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DriverRegLoginActivity extends AppCompatActivity {
     TextView driverStatus, question;//objavlenje vseh widgetov
     Button signInBtn, signUpBtn;
     EditText emailET, passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reg_login);//podkluchenje widjetov k id
        driverStatus = (TextView)findViewById(R.id.statusDriver);
        question = (TextView)findViewById(R.id.accountCreate);
        signInBtn = (Button) findViewById(R.id.signInDriver);
        signUpBtn = (Button) findViewById(R.id.signUpDriver);
        emailET = (EditText) findViewById(R.id.driverEmail);
        passwordET = (EditText) findViewById(R.id.driverPassword);

        signUpBtn.setVisibility(View.INVISIBLE);//button signIn don`t visible
        signUpBtn.setEnabled(false);//can`t press the button signIn occasionally when it not visible

        question.setOnClickListener(new View.OnClickListener() {//knopka sign in ne vidna poka ne narzat na nadpis "you don`t have account
            @Override
            public void onClick(View view) {
                signInBtn.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(true);
                driverStatus.setText("Registration for the drivers");//izmenenie texta nadpisi "log in for drivers"
            }
        });



    }
}