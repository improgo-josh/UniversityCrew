package com.example.university;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    static final String TAG = "LoginActivity";

    EditText et_email, et_confirmEmail, et_password, et_confirmPassword, et_firstName, et_lastName;
    Button btn_createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_email = findViewById(R.id.et_email);
        et_confirmEmail = findViewById(R.id.et_confirmEmail);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        et_password = findViewById(R.id.et_password);
        btn_createAccount = findViewById(R.id.btn_createAccount);

        btn_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String confirmEmail = et_confirmEmail.getText().toString();
                String password = et_password.getText().toString();
                String confirmPassword = et_confirmPassword.getText().toString();

                if (!hasEmptyFields())
                    if (isConfirmed(email, confirmEmail, password, confirmPassword))
                        if (isUniversityEmail(email))
                            pullVerificationFragment();
            }
        });

    }

    private boolean isConfirmed(String email, String confirmEmail, String password, String confirmPassword) {
        boolean isConfirmed = true;
        boolean unconfirmedPassword = !password.equals(confirmPassword), unconfirmedEmail = !email.equalsIgnoreCase(confirmEmail);
        String unmatchedEmailAndPass = "Emails and Passwords do not match";
        String unmatchedPass = "Passwords do not match";
        String unmatchedEmail = "Emails do not match";

        if (unconfirmedEmail && unconfirmedPassword) {
            Log.d(TAG, unmatchedEmailAndPass);
            Toast.makeText(getApplicationContext(), unmatchedEmailAndPass, Toast.LENGTH_SHORT).show();
            isConfirmed = false;
        } else if (unconfirmedEmail) {
            Log.d(TAG, unmatchedEmail);
            Toast.makeText(getApplicationContext(), unmatchedEmail, Toast.LENGTH_SHORT).show();
            isConfirmed = false;
        } else if (unconfirmedPassword){
            Log.d(TAG, unmatchedPass);
            Toast.makeText(getApplicationContext(), unmatchedPass, Toast.LENGTH_SHORT).show();
            isConfirmed = false;
        }

        return isConfirmed;
    }

    private boolean hasEmptyFields() {
        boolean hasEmptyFields = false;

        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        String email = et_email.getText().toString();
        String confirmEmail = et_confirmEmail.getText().toString();
        String password = et_password.getText().toString();
        String confirmPassword = et_confirmPassword.getText().toString();
        String emptyFields = "Empty fields";
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Log.d(TAG, emptyFields);
            Toast.makeText(getApplicationContext(), emptyFields, Toast.LENGTH_SHORT).show();
            hasEmptyFields = true;
        }

        return hasEmptyFields;
    }
    private boolean isUniversityEmail(String email) {
        return true;
    }

    private void pullVerificationFragment() {

    }

    private boolean isUnregistered(String email) {
        return false;
    }
}
