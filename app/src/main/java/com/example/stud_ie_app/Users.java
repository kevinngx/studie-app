package com.example.stud_ie_app;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.stud_ie_app.DatabaseClasses.SessionData;

import java.util.List;

@Entity
public class Users {
    @NonNull
    @PrimaryKey
    private String userName;

    private String password;
    private int score;
    private int avatar;
    private String role;

    @Ignore
    public Users(String userName, String password, int avatar) {
            this.userName = userName;
            this.password = password;
            this.score = 0;
            this.role = "Intern";
            this.avatar = avatar;
    }

    public Users(@NonNull String userName, String password, int score, int avatar, String role) {
        this.userName = userName;
        this.password = password;
        this.score = score;
        this.avatar = avatar;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", avatar=" + avatar +
                ", role='" + role + '\'' +
                '}';
    }

    public boolean hasBadge(int badgeId) {
        // Returns true if they have the badge
        boolean hasBadge = false;
        List<UsrBadges> userBadges = SessionData.mUsrBadgesDatabase.mUsrBadgesDao().getAllBadgesByUser(SessionData.currentUser.getUserName());
        for (int i = 0; i < userBadges.size(); i++) {
            if (userBadges.get(i).getBadgeID() == badgeId) {
                hasBadge = true;
            }
        }
        return hasBadge;
    }
}