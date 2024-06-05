package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.stud_ie_app.DatabaseClasses.SessionData;

public class RegistrationActivity extends AppCompatActivity {

    public final static String NEW_USERNAME ="com.example.stud_ie_app.new_username";
    public final static String NEW_PASSWORD ="com.example.stud_ie_app.new_password";

    private TextView newUsername;
    private TextView newPasswordOne;
    private TextView newPasswordTwo;
    private TextView registrationErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        newUsername = findViewById(R.id.newUsername);
        newPasswordOne = findViewById(R.id.newPasswordOne);
        newPasswordTwo = findViewById(R.id.newPasswordTwo);
        registrationErrorMessage = findViewById(R.id.registrationErrorMessage);
        registrationErrorMessage.setText("");

    }

    public void onSubmitButtonPress(View view) {

        // Error checking, making sure fields are correct
        if(newUsername.getText().toString().equals("")) {
            registrationErrorMessage.setText("Please fill out the username field");
        } else if (newPasswordOne.getText().toString().equals("") || newPasswordTwo.getText().toString().equals("")) {
            registrationErrorMessage.setText("Please fill out both password fields");
        } else if (newPasswordOne.getText().toString().equals(newPasswordTwo.getText().toString()) == false) {
            registrationErrorMessage.setText("Passwords did not match");
        } else if (SessionData.mUserDatabase.mUserDao().fetchOneUserByUserName(newUsername.getText().toString()) != null){
            registrationErrorMessage.setText("Username taken");
        } else{
            // Correct credentials, passes username and password to page 2 of the registration process
            System.out.println(String.format("LOGIN DETAILS PASSED \nUsername: %s \nPassword: %s \nPasswrod: %s",
                    newUsername.getText(), newPasswordOne.getText(), newPasswordTwo.getText()));
            Intent intent = new Intent(this, RegistrationTwoActivity.class);
            intent.putExtra(NEW_USERNAME, newUsername.getText().toString());
            intent.putExtra(NEW_PASSWORD, newPasswordOne.getText().toString());
            startActivity(intent);
        }

    }

    public void onBackToLoginButtonPress(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
