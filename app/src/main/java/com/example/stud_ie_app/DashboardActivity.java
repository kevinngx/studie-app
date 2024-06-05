package com.example.stud_ie_app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.DashboardFragments.FragmentLeaderboard;
import com.example.stud_ie_app.DashboardFragments.FragmentQuiz;
import com.example.stud_ie_app.DashboardFragments.FragmentUser;
import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.RecyclerViewAdapters.RoleBadgesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = "DashboardActivity";
    public static final String CATEGORY = "category";

    private TabLayout dashboardTabLayout;
    private AppBarLayout dashboardAppBarLayout;
    private ViewPager dashboardViewPager;
    private List<Badges> mBadges;

    Dialog mDialog;

    ImageView userAvatar;
    TextView userUsername;
    TextView userRole;
    TextView userScore;

    ImageView updateAvatar;
    TextView updateUsername;
    TextView updateRole;
    TextView updatePoints;
    int selectedAvatar = SessionData.currentUser.getAvatar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashboardTabLayout = (TabLayout) findViewById(R.id.dashboard_tab_layout);
        dashboardAppBarLayout = (AppBarLayout) findViewById(R.id.dashboard_app_bar);
        dashboardViewPager = (ViewPager) findViewById(R.id.dashboard_viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Adding fragments to the ViewPager
        adapter.addFragments(new FragmentUser(), "User");
        adapter.addFragments(new FragmentQuiz(), "Quiz");
        adapter.addFragments(new FragmentLeaderboard(), "Leaderboard");

        // Setting up Adapter
        dashboardViewPager.setAdapter(adapter);
        dashboardTabLayout.setupWithViewPager(dashboardViewPager);

        // Set up dialog popup
        mDialog = new Dialog(this);

        setupUserId();
    }

    private void setupUserId() {
        // Sets up the userId card displayed at the top of the dashboard
        userAvatar = (ImageView) findViewById(R.id.user_avatar);
        userUsername = (TextView) findViewById(R.id.leaderboard_username);
        userRole = (TextView) findViewById(R.id.user_role);
        userScore = (TextView) findViewById(R.id.user_score);

        userAvatar.setImageResource(ImageBank.avatars[SessionData.currentUser.getAvatar()]);
        userUsername.setText(SessionData.currentUser.getUserName());
        userRole.setText(SessionData.currentUser.getRole());
        userScore.setText(Integer.toString(SessionData.currentUser.getScore()));

    }

    public void onLevelSelect(View view) {
        // Identifies which level is selected and launches the Quiz Activity for that level

        // Create an index of all of the level cards;
        int[] levels = {
                R.id.level_transport, // 0
                R.id.level_beach, // 1
                R.id.level_circus, // 2
                R.id.level_jobs, // 3
                R.id.level_weather, // 4
                R.id.level_nature, // 5
                R.id.level_music, // 6
                R.id.level_exercise, // 7
                R.id.level_politics, // 8
                R.id.level_astronomy // 9
        };

        // Matches the card selected with the index of the level
        int levelId = 0;
        while (levelId < levels.length) {
            if (view.getId() == levels[levelId])
                break;
            levelId++;
        }

        // Launches Quiz Activity
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(CATEGORY, QuestionBank.categories[levelId]);
        startActivity(intent);
    }

    public void onUpdateRoleButtonClick(View view) {
        // Opens a dialog menu where the user can update their role/job title
        mDialog.setContentView(R.layout.popup_update_role);

        Button popupBack = (Button) mDialog.findViewById(R.id.popup_back);
        Button popupApply = (Button) mDialog.findViewById(R.id.popup_apply);
        Button popupSave = (Button) mDialog.findViewById(R.id.popup_save);

        RecyclerView roleBadgeRecyclerView;

        updateAvatar = (ImageView) mDialog.findViewById(R.id.update_avatar);
        updateUsername = (TextView) mDialog.findViewById(R.id.update_username);
        updateRole = (TextView) mDialog.findViewById(R.id.update_role);
        updatePoints = (TextView) mDialog.findViewById(R.id.update_score);

        updateAvatar.setImageResource(ImageBank.avatars[SessionData.currentUser.getAvatar()]);
        updateUsername.setText(SessionData.currentUser.getUserName());
        updateRole.setText(SessionData.currentUser.getRole());
        updatePoints.setText(Integer.toString(SessionData.currentUser.getScore()));

        // Setup RecyclerView of their badges to choose from
        getUserBadges();
        roleBadgeRecyclerView = (RecyclerView) mDialog.findViewById(R.id.role_recyclerView);
        roleBadgeRecyclerView.setHasFixedSize(true);
        RoleBadgesRecyclerViewAdapter recyclerViewAdapter = new RoleBadgesRecyclerViewAdapter(mDialog.getContext(), mBadges);
        roleBadgeRecyclerView.setLayoutManager(new LinearLayoutManager(mDialog.getContext(), LinearLayoutManager.HORIZONTAL, false));
        roleBadgeRecyclerView.setAdapter(recyclerViewAdapter);

        // Closes dialog menu
        popupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        // Allow the user to preview the job title on the preview card
        popupApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SessionData.selectedRole.equals("DEFAULT")) {
                    updateRole.setText(SessionData.selectedRole);
                }
            }
        });

        // Update the job title to the badge selected
        popupSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SessionData.selectedRole.equals("DEFAULT")) {
                    updateRole.setText(SessionData.selectedRole);
                    String newRole = updateRole.getText().toString();
                    SessionData.currentUser.setRole(newRole);
                    userRole.setText(newRole);
                    SessionData.mUserDatabase.mUserDao().updateRole(newRole, SessionData.currentUser.getUserName());
                }
            }
        });

        // Launch dialog window
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.show();

    }

    private void getUserBadges() {
        // Gets all of the badges the user has
        List<UsrBadges> userBadges = SessionData.mUsrBadgesDatabase.mUsrBadgesDao().getAllBadgesByUser(SessionData.currentUser.getUserName());
        mBadges = new ArrayList<Badges>();
        for (int i = 0; i < userBadges.size(); i++) {
            mBadges.add(SessionData.mBadgeDatabase.mBadgeDao().fetchBadgeByID(userBadges.get(i).getBadgeID()));
        }
    }

    public void onUpdateAvatarButtonClick(View view) {
        // Opens a dialog menu where the user can update their avatar

        mDialog.setContentView(R.layout.popup_update_avatar);

        // Set onClickListeners
        Button popupBack = (Button) mDialog.findViewById(R.id.popup_back);
        Button popupSubmit = (Button) mDialog.findViewById(R.id.popup_submit);

        updateAvatar = (ImageView) mDialog.findViewById(R.id.update_avatar);
        updateUsername = (TextView) mDialog.findViewById(R.id.update_username);
        updateRole = (TextView) mDialog.findViewById(R.id.update_role);
        updatePoints = (TextView) mDialog.findViewById(R.id.update_score);

        updateAvatar.setImageResource(ImageBank.avatars[SessionData.currentUser.getAvatar()]);
        updateUsername.setText(SessionData.currentUser.getUserName());
        updateRole.setText(SessionData.currentUser.getRole());
        updatePoints.setText(Integer.toString(SessionData.currentUser.getScore()));

        // Closes dialog menu
        popupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        // Update the user's avatar to the avatar selected
        popupSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAvatar.setImageResource(ImageBank.avatars[selectedAvatar]);
                SessionData.mUserDatabase.mUserDao().updateAvatar(selectedAvatar, SessionData.currentUser.getUserName());
            }
        });

        // Launch dialog window
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.show();
    }

    public void onUpdatePasswordButtonClick(View view) {
        // Opens a dialog menu where the user can update their password

        mDialog.setContentView(R.layout.popup_update_password);

        // Set onClickListeners
        Button popupBack = (Button) mDialog.findViewById(R.id.popup_back);
        Button popupSubmit = (Button) mDialog.findViewById(R.id.popup_submit);

        final EditText currentPassword = (EditText) mDialog.findViewById(R.id.update_currentPassword);
        final EditText newPasswordOne = (EditText) mDialog.findViewById(R.id.update_newPasswordOne);
        final EditText newPasswordTwo = (EditText) mDialog.findViewById(R.id.update_newPasswordTwo);
        final TextView warningMessage = (TextView) mDialog.findViewById(R.id.update_warningMessage);

        popupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        popupSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check password validity before updating password
                if (currentPassword.getText().toString().equals("") || newPasswordOne.getText().toString().equals("") || newPasswordTwo.getText().toString().equals("")) {
                    warningMessage.setTextColor(Color.RED);
                    warningMessage.setText("Please enter all fields");

                } else if (!currentPassword.getText().toString().equals(SessionData.currentUser.getPassword())) {
                    warningMessage.setTextColor(Color.RED);
                    warningMessage.setText("Incorrect Current Password");

                } else if (!newPasswordOne.getText().toString().equals(newPasswordTwo.getText().toString())) {
                    warningMessage.setTextColor(Color.RED);
                    warningMessage.setText("Passwords do not match");

                } else {
                    // Credentials match and updates the password
                    warningMessage.setTextColor(Color.GREEN);
                    warningMessage.setText("Password succesfully changed");
                    SessionData.mUserDatabase.mUserDao().updatePassword(newPasswordOne.getText().toString(), SessionData.currentUser.getUserName());
                }
            }
        });

        // Launch dialog window
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.show();
    }

    public void onLogoutButtonClick(View view) {
        // Logs out of the app
        Log.d(TAG, "onLogoutButtonClick: pressed");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onOnbardingButtonClick(View view) {
        // Returns to the onboarding slide
        Log.d(TAG, "onOnbardingButtonClick: pressed");
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

    public void onAvatarSelectPress(View view) {
        // Updates preview of the avatar based on selected avater
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

        selectedAvatar = 0;
        while (selectedAvatar < avatars.length) {
            if (view.getId() == avatars[selectedAvatar])
                break;
            selectedAvatar++;
        }
        updateAvatar.setImageResource(ImageBank.avatars[selectedAvatar]);
    }

}
