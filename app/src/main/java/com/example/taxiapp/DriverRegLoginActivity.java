package com.example.taxiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverRegLoginActivity extends AppCompatActivity {
     TextView driverStatus, question;//objavlenje vseh widgetov
     Button signInBtn, signUpBtn;
     EditText emailET, passwordET;

     FirebaseAuth mAuth;

     ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);

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

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();//preobrazovanie vvedennogo email v strochku string
                String password = passwordET.getText().toString();//preobrazovanie vvedennogo parolya v strochku string

                RegisterDriver(email, password);


            }
        });
    }

    private void RegisterDriver(String email, String password){
        loadingBar.setTitle("User registration");
        loadingBar.setMessage("Please wait loading");
        loadingBar.show();
      mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()) {
                  Toast.makeText(DriverRegLoginActivity.this, "Registration completed successfully", Toast.LENGTH_SHORT).show();
                  loadingBar.dismiss();
              } else {
                  Toast.makeText(DriverRegLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                  loadingBar.dismiss();
              }
          }
      });
    }
}