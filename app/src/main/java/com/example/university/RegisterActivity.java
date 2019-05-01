package com.example.university;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;
import java.util.regex.Pattern;

// TODO: View all registered Users
// TODO: Push data into database


public class RegisterActivity extends AppCompatActivity {

    static final String TAG = "RegisterActivity";
    static final String EMAIL_KEY = "email";
    static final String FIRST_NAME_KEY = "firstName";
    static final String LAST_NAME_KEY = "lastName";

    // TODO: Change EditTexts show Error Messages under bar, possibly wrap EditTexts in MaterialEditText
    // TODO: Email Address Pattern - Change it to be EDU only and then remove "isUniversity" method
    static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    // TODO: Password Pattern
    // static final Pattern PASSWORD_PATTERN = Pattern.compile();

    boolean isUnregistered;

    EditText et_email, et_confirmEmail, et_password, et_confirmPassword, et_firstName, et_lastName;
    EditText[] editText;
    Button btn_createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_email = findViewById(R.id.et_email);
        et_confirmEmail = findViewById(R.id.et_confirmEmail);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        et_password = findViewById(R.id.et_password);
        btn_createAccount = findViewById(R.id.btn_createAccount);

        editText = new EditText[]{et_firstName, et_lastName, et_email, et_confirmEmail, et_password, et_confirmPassword};

        btn_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = et_firstName.getText().toString();
                String lastName = et_lastName.getText().toString();
                String email = et_email.getText().toString();
                String confirmEmail = et_confirmEmail.getText().toString();
                String password = et_password.getText().toString();
                String confirmPassword = et_confirmPassword.getText().toString();

                /*if (!hasEmptyFields(firstName, lastName, email, confirmEmail, password, confirmPassword))
                    if (isConfirmed(email, confirmEmail, password, confirmPassword)) {
                        Log.d(TAG, "Confirmed");
                        if (isUniversityEmail(email)) {
                            Log.d(TAG, "isUniversityEmail");
                            if (isUnregistered(email))

                                pullVerificationFragment();

                        }
                    }*/
                if (hasEmptyFields()) {
                    et_firstName.setError("Cannot leave field empty");
                    if (!isConfirmed(email, confirmEmail, password, confirmPassword)) {
                        Log.d(TAG, "Confirmed");
                        if (isUniversityEmail(email)) {
                            Log.d(TAG, "isUniversityEmail");
                            if (isUnregistered(email))

                                pullVerificationFragment();

                        }
                    }
                }
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


    private boolean hasEmptyFields(/*String firstName, String lastName, String email, String confirmEmail, String password, String confirmPassword*/) {
        final int SIZE = 6;
        EditText[] emptyFields = new EditText[SIZE];

        boolean hasEmptyFields = false;

        for (int i = 0; i < SIZE; i++) {
            if (editText[i].getText().toString().isEmpty()) {
                editText[i].setError("Cannot leave field Empty");
                hasEmptyFields = true;
            }
        }
        /*
        String emptyFields = "Empty fields";
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Log.d(TAG, emptyFields);
            Toast.makeText(getApplicationContext(), emptyFields, Toast.LENGTH_SHORT).show();
            hasEmptyFields = true;
        }*/

        return hasEmptyFields;
    }
    private boolean isUniversityEmail(String email) {
        String key = ".edu";
        String tag = "";
        int emailLength = email.length();
        int keyLength = key.length();

        if (emailLength <= keyLength)
            return false;

        for (int i = emailLength-1; i >= emailLength-keyLength; i--)
            tag = email.charAt(i) + tag;

        return tag.equalsIgnoreCase(key);
    }

    private void pullVerificationFragment() {
        sendEmailToUser();


        if (isVerified()) {
            String firstName = et_firstName.getText().toString();
            String lastName = et_lastName.getText().toString();
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            createNewAccount(firstName, lastName, email, password);
        }
    }

    private boolean isVerified() {
        // TODO: Verify code
        return true;
    }

    private void sendEmailToUser() {
        // TODO: Send an Email to User with Code to confirm user is not a robot and email is active
    }

    private boolean isUnregistered(String email) {
        isUnregistered = false;

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo(EMAIL_KEY, email);

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    isUnregistered = true;
                } else {

                    Log.d(TAG, e.getMessage());
                }
            }
        });

        //        for (int i = )


        return isUnregistered;
    }

    private void createNewAccount(String firstName, String lastName, String email, String password) {
        ParseUser user = new ParseUser();

        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(password);
        user.put(FIRST_NAME_KEY, firstName);
        user.put(LAST_NAME_KEY, lastName);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    goToActivity(LoginActivity.class);
                } else {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }

    private void goToActivity(Class c) {
        Intent i = new Intent(this, c);
        startActivity(i);
        finish();
    }
}
