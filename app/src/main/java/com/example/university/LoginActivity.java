package com.example.university;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * TODO: 1. Get Text from ET Password and ET UniMail
 * TODO: 2. Verify that the Password and UniMail are the same
 * TODO: 3. Button setOnClickListener handles the Verification
 * TODO: 4. Forgot password --> Send an E-Mail
 * TODO: 5. NoAccount --> Send to Register Screen
 */


public class LoginActivity extends AppCompatActivity {

    static final String TAG = "LoginActivity";

    ProgressDialog progressDialog;
    // Initialize Variables
    ImageView iv_appLogo;
    EditText et_password;
    EditText et_uniMail;
    Button bt_signIn;
    TextView tv_forgotPassword;
    TextView tv_noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        // Set Views
        iv_appLogo = findViewById(R.id.iv_appLogo);
        et_password = findViewById(R.id.et_password);
        et_uniMail = findViewById(R.id.et_uniMail);
        bt_signIn = findViewById(R.id.bt_signIn);
        tv_forgotPassword = findViewById(R.id.tv_forgotPassword);
        tv_noAccount = findViewById(R.id.tv_noAccount);


        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "bt_signIn Clicked");

                // TODO: 1, 2, 3
                String uniMail = et_uniMail.getText().toString();
                String password = et_password.getText().toString();
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

    private void login(String email, String password) {
        // TODO: 2
        // TODO: Send a request to get the information from database
        Log.e(TAG, "1 " + email + " " + password);

        progressDialog.show();

        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

//                Log.e(TAG, "1" + e.getMessage());
                if (e != null) {
//                    Log.e(TAG, "Issue with login " + e.getMessage());
                    e.printStackTrace();
                    progressDialog.dismiss();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Successfully logged in!", Toast.LENGTH_SHORT).show();
                goToActivity(MainActivity.class);
                progressDialog.dismiss();
            }
        });
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goToRegisterActivity() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    private void goToActivity(Class c) {
        Intent i = new Intent(this, c);
        startActivity(i);
        finish();
    }
}
