package com.example.taxiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerRegLoginActivity extends AppCompatActivity {

    TextView customerStatus, question;//objavlenje vseh widgetov
    Button signInBtn, signUpBtn;
    EditText emailET, passwordET;


    FirebaseAuth mAuth;

    ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg_login);

        mAuth = FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);

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

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();//preobrazovanie vvedennogo email v strochku string
                String password = passwordET.getText().toString();//preobrazovanie vvedennogo parolya v strochku string

                RegisterCustomer(email, password);


            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();//preobrazovanie vvedennogo email v strochku string
                String password = passwordET.getText().toString();//preobrazovanie vvedennogo parolya v strochku string

                SignInCustomer(email, password);
            }
        });
    }

    private void SignInCustomer(String email, String password)
    {
        loadingBar.setTitle("Customer account login");
        loadingBar.setMessage("Please wait loading");
        loadingBar.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerRegLoginActivity.this, "Login completed successfully", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                } else {
                    Toast.makeText(CustomerRegLoginActivity.this, "Error, try again", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void RegisterCustomer(String email, String password) {

        loadingBar.setTitle("User registration");
        loadingBar.setMessage("Please wait loading");
        loadingBar.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerRegLoginActivity.this, "Registration completed successfully", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                } else {
                    Toast.makeText(CustomerRegLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}