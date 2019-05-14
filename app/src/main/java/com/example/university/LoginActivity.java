package com.example.university;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


// Email Verification is finished
/**
 * TODO: 1. Get Text from ET Password and ET UniMail
 * TODO: 2. Verify that the Password and UniMail are the same
 * TODO: 3. Button setOnClickListener handles the Verification
 * TODO: 4. Forgot password --> Send an E-Mail
 * TODO: 5. NoAccount --> Send to Register Screen
 */


public class LoginActivity extends AppCompatActivity {

    ActionBar actionBar;
    static final String TAG = "LoginActivity";
    static final String EMAIL_VERIFIED_KEY = "emailVerified";
    ProgressDialog progressDialog;
    // Initialize Variables
    ImageView iv_appLogo;
    TextInputEditText tiet_password;
    TextInputEditText tiet_uniMail;
    Button bt_signIn;
    TextView tv_forgotPassword;
    TextView tv_noAccount;
    TextInputLayout til_uniMail;
    TextInputLayout til_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        // Set Views
        iv_appLogo = findViewById(R.id.iv_appLogo);
        tiet_password = findViewById(R.id.tiet_password);
        tiet_uniMail = findViewById(R.id.tiet_uniMail);

        til_uniMail = findViewById(R.id.til_uniMail);
        til_password = findViewById(R.id.til_password);

        bt_signIn = findViewById(R.id.bt_signIn);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);
        tv_noAccount = findViewById(R.id.tv_noAccount);


        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "bt_signIn Clicked");

                // TODO: 1, 2, 3
                String uniMail = tiet_uniMail.getText().toString();
                String password = tiet_password.getText().toString();

//                if (!hasEmptyFields(uniMail, password))
                    login(uniMail, password);

            }
        });

        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "tv_forgot Clicked");

                // TODO: 4
            }
        });

        tv_noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "tv_noAccount Clicked");

                // TODO: 5

                goToActivity(RegisterActivity.class);
            }
        });
    }
    // Test Post

    private void login(String email, String password) {
        // TODO: 2
        // TODO: Send a request to get the information from database
        Log.e(TAG, "1 " + email + " " + password);

        progressDialog.show();

        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                String message = "";
                if (e != null) {
                    e.printStackTrace();
                    // CODE 200 - username/email is required.
                    // CODE 201 - password is required.
                    // CODE 101 - Invalid username/password.

                    progressDialog.dismiss();
                    message = e.getMessage();
                    int code = e.getCode();
                    if (code == 200)
                        message = "Email is required";
                    else if (code == 201)
                        message = "Password is required";

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if (user.getBoolean(EMAIL_VERIFIED_KEY)) {*/
                    message = "Successfully logged in!";
                    goToActivity(CoursesActivity.class);
                /*} else {
                    message = "Email is not verified";
                }*/
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    private void goToActivity(Class c) {
        Intent i = new Intent(this, c);
        startActivity(i);
        finish();
    }

    private void onForgotPassword() {
        // TODO: Ask for Email to send a code
    }

    // TODO: Code Generator?

    private boolean hasEmptyFields(String uniMail, String password) {
        boolean hasEmptyFields = false;

        if (uniMail.isEmpty()) {
            hasEmptyFields = true;
            til_uniMail.setError("Field cannot be empty");
        }
        if (password.isEmpty()) {
            hasEmptyFields = true;
            til_password.setError("Field cannot be empty");
        }

        return hasEmptyFields;
    }
}
