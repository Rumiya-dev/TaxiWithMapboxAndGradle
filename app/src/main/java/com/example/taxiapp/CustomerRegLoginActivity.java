package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerRegLoginActivity extends AppCompatActivity {

    TextView customerStatus, question;//objavlenje vseh widgetov
    Button signInBtn, signUpBtn;
    EditText emailET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg_login);

        customerStatus = (TextView)findViewById(R.id.statusCustomer);
        question = (TextView)findViewById(R.id.accountCreateCustomer);
        signInBtn = (Button) findViewById(R.id.signInCustomer);
        signUpBtn = (Button) findViewById(R.id.signUpCustomer);
        emailET = (EditText) findViewById(R.id.customerEmail);
        passwordET = (EditText) findViewById(R.id.customerPassword);

        signUpBtn.setVisibility(View.INVISIBLE);//button signIn don`t visible
        signUpBtn.setEnabled(false);//can`t press the button signIn occasionally when it not visible

        question.setOnClickListener(new View.OnClickListener() {//knopka sign in ne vidna poka ne narzat na nadpis "you don`t have account
            @Override
            public void onClick(View view) {
                signInBtn.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(true);
                customerStatus.setText("Registration for the customers");//izmenenie texta nadpisi "log in for drivers"
            }
        });
    }
}