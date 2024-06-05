package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.DatabaseClasses.SessionData;

public class RegistrationTwoActivity extends AppCompatActivity {

    ImageView id_image;
    TextView id_username;
    String newUsername;
    String newPassword;
    int newAvatar = 0; // Default selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);

        id_image = (ImageView) findViewById(R.id.user_avatar);
        id_username = (TextView) findViewById(R.id.leaderboard_username);

        // Retrieves data from previous activity
        Bundle bundle = getIntent().getExtras();
        newUsername = bundle.getString(RegistrationActivity.NEW_USERNAME);
        newPassword = bundle.getString(RegistrationActivity.NEW_PASSWORD);

        // Display username on card
        id_username.setText(newUsername);

    }

    public void onButtonPress(View view) {
        // Array of CardViews containing avatars selected
        int[] avatars = {
                R.id.avatar_zero,
                R.id.avatar_one,
                R.id.avatar_two,
                R.id.avatar_three,
                R.id.avatar_four,
                R.id.avatar_five,
                R.id.avatar_six,
                R.id.avatar_seven,
                R.id.avatar_eight,
        };

        // Find avatar based on the index of the card selected
        int newAvatar = 0;
        while (newAvatar < avatars.length) {
            if (view.getId() == avatars[newAvatar])
                break;
            newAvatar++;
        }

        // Update Employee id_card
        id_image.setImageResource(ImageBank.avatars[newAvatar]);

    }

    public void onSubmitButtonPress(View view) {
        //create user from credentials passed and adds them to the database using an async thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                Users user = new Users(bundle.getString(RegistrationActivity.NEW_USERNAME), bundle.getString(RegistrationActivity.NEW_PASSWORD), newAvatar );
                SessionData.mUserDatabase.mUserDao().insertOnlySingleUser(user);
                SessionData.currentUser = user;
            }
        }) .start();

        // Goes to onboarding process
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

    public void onBackToLoginButtonPress(View view) {
        // Returns to login screen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
