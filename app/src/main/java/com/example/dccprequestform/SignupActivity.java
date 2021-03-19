package com.example.dccprequestform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btnSignup = findViewById(R.id.btnSignUpSubmit);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsername = findViewById(R.id.etUsername2);
                String username = etUsername.getText().toString();

                EditText etPassword = findViewById(R.id.etPassword2);
                String password = etPassword.getText().toString();

                EditText etFirstName = findViewById(R.id.etFirstName);
                String firstName = etFirstName.getText().toString();

                EditText etMiddleName = findViewById(R.id.etMiddleName);
                String middleName = etMiddleName.getText().toString();

                EditText etLastName = findViewById(R.id.etLastName);
                String lastName = etLastName.getText().toString();

                EditText etEmail = findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etContactNo = findViewById(R.id.etContactNo);
                String contactNo = etContactNo.getText().toString();

                User user = new User(username, password, firstName, middleName, lastName, email, contactNo);

                WebAPI webAPI = new WebAPI(getApplication());
                webAPI.register(user, new RegisterListener() {
                    @Override
                    public void onRegister(boolean registered) {
                        if(registered)
                        {
                            finish();
                        }
                    }
                });
            }
        });
    }
}