package com.example.stud_ie_app.DatabaseClasses;

import android.support.annotation.NonNull;

import com.example.stud_ie_app.Badges;
import com.example.stud_ie_app.Users;
import com.example.stud_ie_app.UsrBadges;

import java.util.ArrayList;
import java.util.List;

public class CreateData {

    public static void populateUsers() {
        // Create dummy data users for testing
//        List<Users> currentUsersDatabase = SessionData.mUserDatabase.mUserDao().getAll();
        List<Users> dummyUsers = new ArrayList<>();
        dummyUsers.add(new Users("Thanos", "password", 120000, 1, "God"));
        dummyUsers.add(new Users("Spiderman", "password", 55000, 2, "Executive"));
        dummyUsers.add(new Users("Hulk", "password", 49000, 5, "Executive"));
        dummyUsers.add(new Users("Captain America", "password", 32000, 8, "Executive"));
        dummyUsers.add(new Users("Thor", "password", 26800, 7, "Executive"));
        dummyUsers.add(new Users("Black Panther", "password", 24600, 0, "Manager"));
        dummyUsers.add(new Users("Doctor Strange", "password", 12300, 5, "Manager"));
        dummyUsers.add(new Users("Hawkeye", "password", 8800, 8, "Senior"));
        dummyUsers.add(new Users("Groot", "password", 5600, 2, "Senior"));
        dummyUsers.add(new Users("Ant-Man", "password", 4700, 3, "Graduate"));
        dummyUsers.add(new Users("Falcon", "password", 3200, 5, "Graduate"));
        dummyUsers.add(new Users("Vision", "password", 1400, 7, "Graduate"));
        dummyUsers.add(new Users("War-Machine", "password", 800, 8, "Intern"));
        dummyUsers.add(new Users("Starlord", "password", 200, 0, "Intern"));

        SessionData.mUserDatabase.mUserDao().insertMultipleUsers(dummyUsers);
        // Insert if new user
//        for (int i = 0; i < dummyUsers.size(); i++) {
//            if (!currentUsersDatabase.contains(dummyUsers.get(i))){
//                SessionData.mUserDatabase.mUserDao().insertOnlySingleUser(dummyUsers.get(i));
//            }
//        }

    }

    public static void populateUserBadges() {
        // This method will assign the score based badges to all of the dummy data users
        List<Users> users = SessionData.mUserDatabase.mUserDao().getAll();
        for (int i = 0; i < users.size(); i++) {
            List<UsrBadges> currentUserBadges = SessionData.mUsrBadgesDatabase.mUsrBadgesDao().getAllBadgesByUser(users.get(i).getUserName());

            // Intern
            boolean hasIntern = false;
            for (int n = 0; n < currentUserBadges.size(); n++) {
                if (currentUserBadges.get(n).getBadgeID() == 1) {
                    hasIntern = true;
                }
            }
            if(hasIntern == false) {
                SessionData.mUsrBadgesDatabase.mUsrBadgesDao().insertSingleBadge(new UsrBadges(users.get(i).getUserName(), 1));
            }


            // Graduate
            if (users.get(i).getScore() >= 1000) {
                boolean hasGraduate = false;
                for (int n = 0; n < currentUserBadges.size(); n++) {
                    if (currentUserBadges.get(n).getBadgeID() == 2) {
                        hasGraduate = true;
                    }
                }
                if(hasGraduate == false) {
                    SessionData.mUsrBadgesDatabase.mUsrBadgesDao().insertSingleBadge(new UsrBadges(users.get(i).getUserName(), 2));
                }
            }

            // Senior
            if (users.get(i).getScore() >= 5000) {
                boolean hasSenior = false;
                for (int n = 0; n < currentUserBadges.size(); n++) {
                    if (currentUserBadges.get(n).getBadgeID() == 3) {
                        hasSenior = true;
                    }
                }
                if(hasSenior == false) {
                    SessionData.mUsrBadgesDatabase.mUsrBadgesDao().insertSingleBadge(new UsrBadges(users.get(i).getUserName(), 3));
                }
            }

            // Manager
            if (users.get(i).getScore() >= 10000) {
                boolean hasManager = false;
                for (int n = 0; n < currentUserBadges.size(); n++) {
                    if (currentUserBadges.get(n).getBadgeID() == 4) {
                        hasManager = true;
                    }
                }
                if(hasManager == false) {
                    SessionData.mUsrBadgesDatabase.mUsrBadgesDao().insertSingleBadge(new UsrBadges(users.get(i).getUserName(), 4));
                }
            }

            // Exec
            if (users.get(i).getScore() >= 25000) {
                boolean hasExec= false;
                for (int n = 0; n < currentUserBadges.size(); n++) {
                    if (currentUserBadges.get(n).getBadgeID() == 5) {
                        hasExec = true;
                    }
                }
                if(hasExec == false) {
                    SessionData.mUsrBadgesDatabase.mUsrBadgesDao().insertSingleBadge(new UsrBadges(users.get(i).getUserName(), 5));
                }
            }
        }
    }

    public static void populateBadgesDatabase() {
        List<Badges> badgesList = new ArrayList();
        badgesList.add(new Badges(1, "Intern", "Welcome to the company! We hope you enjoy your internship!", 1));
        badgesList.add(new Badges(2, "Graduate", "Reached 1,000 points! You have been officially hired at Stud.io and can now access Graduate levels", 2));
        badgesList.add(new Badges(3, "Senior", "Reach 5,000 points! You have shown a lot of promise and can now access Senior levels", 3));
        badgesList.add(new Badges(4, "Manager", "Reach 10,000 points! You are now a manager of a whole team, and ready to tackle Manager levels", 4));
        badgesList.add(new Badges(5, "Executive", "Reach 25,000,000 points! You're one of the big bosses now, show us all how it's done by tackling the challenges only available to our top execs", 5));
        badgesList.add(new Badges(6, "Beach Guru", "5 correct in one session: Beach", 6));
        badgesList.add(new Badges(7, "Transport Guru", "5 correct in one session: Transport", 7));
        badgesList.add(new Badges(8, "Animal Guru", "5 correct in one session: Animals", 8));
        badgesList.add(new Badges(9, "Circus Guru", "5 correct in one session: Circus", 9));
        badgesList.add(new Badges(10, "Job Guru", "5 correct in one session: Jobs", 10));
        badgesList.add(new Badges(11, "Weather Guru", "5 correct in one session: Weather", 11));
        badgesList.add(new Badges(12, "Nature Guru", "5 correct in one session: Nature", 12));
        badgesList.add(new Badges(13, "Music Guru", "5 correct in one session: Music", 13));
        badgesList.add(new Badges(14, "Exercise Guru", "5 correct in one session: Exercise", 14));
        badgesList.add(new Badges(15, "Politics Guru", "5 correct in one session: Politics", 15));
        badgesList.add(new Badges(16, "Astronomy Guru", "5 correct in one session: Astronomy", 16));
        badgesList.add(new Badges(17, "Hustler", "10 questions in one sitting", 17));
        badgesList.add(new Badges(18, "Genius", "Got 3 questions correct in a row! You're quite the genius aren't you.", 18));
        badgesList.add(new Badges(19, "LinkedIn Stalker", "Check out the Leaderboard! ", 19));
        badgesList.add(new Badges(20, "Curious Learner", "Learned about other usages for a word", 20));
        badgesList.add(new Badges(21, "Fired", "Get 5 questions incorrect in a row", 21));
        badgesList.add(new Badges(22, "Two Faced", "Updated user avatar. Who are you...?", 22));
        badgesList.add(new Badges(23, "Cyber Security Specialist", "Updated password. Let's hope it was a secure one at least.", 23));
        badgesList.add(new Badges(24, "The Prodigy", "5 Correct in a row for every category. You are a valued employee of this company.", 24));

        // Insert badges if they are not yet in the database
        // Get the list of badges that are currently in the database
//        List<Badges> currentBadgesDatabase = SessionData.mBadgeDatabase.mBadgeDao().getAll();
//            for (int i = 0; i < badgesList.size(); i++) {
//                if (!currentBadgesDatabase.contains(badgesList.get(i))){
//                    SessionData.mBadgeDatabase.mBadgeDao().insertSingleBadge(badgesList.get(i));
//                }
//            }
        SessionData.mBadgeDatabase.mBadgeDao().insertMultipleBadges(badgesList);
    }
}
