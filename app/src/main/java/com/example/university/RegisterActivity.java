package com.example.university;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

// TODO: On TextInputEditText change, set error to be empty
public class RegisterActivity extends AppCompatActivity {

    static final String TAG = "RegisterActivity";
    static final String EMAIL_KEY = "email";
    static final String USER_KEY = "user";
    static final String FIRST_NAME_KEY = "firstName";
    static final String LAST_NAME_KEY = "lastName";

    // TODO: Change TextInputEditTexts show Error Messages under bar, possibly wrap TextInputEditTexts in MaterialTextInputEditText
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

    TextInputLayout til_email, til_confirmEmail, til_password, til_confirmPassword, til_firstName, til_lastName;
    TextInputLayout[] textInputLayout;

    TextInputEditText tiet_email, tiet_confirmEmail, tiet_password, tiet_confirmPassword, tiet_firstName, tiet_lastName;
    TextInputEditText[] textInputEditText;

    Button btn_createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        til_firstName = findViewById(R.id.til_firstName);
        til_lastName = findViewById(R.id.til_lastName);
        til_email = findViewById(R.id.til_email);
        til_confirmEmail = findViewById(R.id.til_confirmEmail);
        til_password = findViewById(R.id.til_password);
        til_confirmPassword = findViewById(R.id.til_confirmPassword);

        tiet_firstName = findViewById(R.id.tiet_firstName);
        tiet_lastName = findViewById(R.id.tiet_lastName);
        tiet_email = findViewById(R.id.tiet_email);
        tiet_confirmEmail = findViewById(R.id.tiet_confirmEmail);
        tiet_confirmPassword = findViewById(R.id.tiet_confirmPassword);
        tiet_password = findViewById(R.id.tiet_password);
        btn_createAccount = findViewById(R.id.btn_createAccount);

        textInputLayout = new TextInputLayout[]{til_email, til_confirmEmail, til_password, til_confirmPassword, til_firstName, til_lastName};
        textInputEditText = new TextInputEditText[]{tiet_email, tiet_confirmEmail, tiet_password, tiet_confirmPassword, tiet_firstName, tiet_lastName};

        btn_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = tiet_firstName.getText().toString();
                String lastName = tiet_lastName.getText().toString();
                String email = tiet_email.getText().toString();
                String confirmEmail = tiet_confirmEmail.getText().toString();
                String password = tiet_password.getText().toString();
                String confirmPassword = tiet_confirmPassword.getText().toString();

                /*if (!hasEmptyFields(firstName, lastName, email, confirmEmail, password, confirmPassword))
                    if (isConfirmed(email, confirmEmail, password, confirmPassword)) {
                        Log.d(TAG, "Confirmed");
                        if (isUniversityEmail(email)) {
                            Log.d(TAG, "isUniversityEmail");
                            if (isUnregistered(email))

                                pullVerificationFragment();

                        }
                    }*/
                if (!hasEmptyFields()) {
                    if (isConfirmed(email, confirmEmail, password, confirmPassword)) {
                        Log.d(TAG, "Confirmed");
                        if (isUniversityEmail(email)) {
                            Log.d(TAG, "isUniversityEmail");
                            pullVerificationFragment();
                        }
                    }
                }
            }
        });

        // TODO: Check all fields are valid
        // TODO: Have them individually checked
        // TODO: Put all addTextChangedListeners into a method that is called when btn is clicked __ DON'T DO
        tiet_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tiet_email.getText().toString().isEmpty())
                    til_email.setError("");
            }
        });

        tiet_confirmEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tiet_confirmEmail.getText().toString().isEmpty())
                    til_confirmEmail.setError("");
            }
        });

        tiet_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tiet_password.getText().toString().isEmpty())
                    til_password.setError("");
            }
        });

        tiet_confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tiet_confirmPassword.getText().toString().isEmpty())
                    til_confirmPassword.setError("");

                // TODO: Check password to make sure it matches
                if (!tiet_password.getText().equals(tiet_confirmPassword.getText()))
                    til_confirmPassword.setError("Password is empty");
            }
        });

        tiet_firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tiet_firstName.getText().toString().isEmpty())
                    til_firstName.setError("");
            }
        });

        tiet_lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tiet_lastName.getText().toString().isEmpty())
                    til_lastName.setError("");
            }
        });


    }

    private boolean isConfirmed(String email, String confirmEmail, String password, String confirmPassword) {
        boolean isConfirmed = true;
        boolean unconfirmedPassword = !password.equals(confirmPassword), unconfirmedEmail = !email.equalsIgnoreCase(confirmEmail);
        String unmatchedPass = "Passwords do not match";
        String unmatchedEmail = "Emails do not match";
        String passMessage = "";
        String emailMessage = "";

        if (unconfirmedEmail) {
            Log.d(TAG, unmatchedEmail);
            emailMessage = unmatchedEmail;
            isConfirmed = false;
        }

        if (unconfirmedPassword) {
            Log.d(TAG, unmatchedPass);
            passMessage = unmatchedPass;
            isConfirmed = false;
        }

        til_email.setError(emailMessage);
        til_confirmEmail.setError(emailMessage);
        til_password.setError(passMessage);
        til_confirmPassword.setError(passMessage);

        return isConfirmed;
    }


    private boolean hasEmptyFields(/*String firstName, String lastName, String email, String confirmEmail, String password, String confirmPassword*/) {
        final int SIZE = 6;
//        TextInputEditText[] emptyFields = new TextInputEditText[SIZE];

        boolean hasEmptyFields = false;

        for (int i = 0; i < SIZE; i++) {
            if (textInputEditText[i].getText().toString().isEmpty()) {
                textInputLayout[i].setError("Cannot leave field Empty");
                hasEmptyFields = true;
            } else {
                textInputLayout[i].setError("");
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
        Log.d(TAG, "VERIFIED");
        sendEmailToUser();


        if (isVerified()) {
            String firstName = tiet_firstName.getText().toString();
            String lastName = tiet_lastName.getText().toString();
            String email = tiet_email.getText().toString();
            String password = tiet_password.getText().toString();

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

    /*
    private boolean isUnregistered(final String email) {
        isUnregistered = false;

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        // Should use whereEqualTo
        query.whereNotEqualTo(USER_KEY, email);
        Log.d(TAG, "EMAIL TO TEST: " + email);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                Log.d(TAG, "WHERE NOT EQUAL TO");
                int size = objects.size();
                if (e == null) {
                    Log.d(TAG, "SIZE: " + size);
                    if (size == 0)
                        // Check that size is 0 (means that the email is not registered in the database)
                        isUnregistered = true;
                    else {
                        isUnregistered = true;
                        for (int i = 0; i < objects.size(); i++) {
                            String getUsername = objects.get(i).getUsername();
                            if (getUsername.equalsIgnoreCase(email)) {
                                isUnregistered = false;
                                til_email.setError("Email is already registered");
                                break;
                            }
                        }
                    }
                } else {
                    Log.d(TAG, "ERROR: " + e.getMessage());
                }
            }
        });
        return isUnregistered;
    }*/

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
                    til_email.setError(e.getMessage());
                    Log.d(TAG, "ERROR: " + e.getMessage());
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
