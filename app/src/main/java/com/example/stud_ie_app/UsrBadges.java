package com.example.stud_ie_app;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity (primaryKeys = {"username", "badgeID"})
public class UsrBadges {

    @NonNull
    private String username;
    @NonNull
    private int badgeID;


    public UsrBadges(@NonNull String username, int badgeID) {
        this.username = username;
        this.badgeID = badgeID;
    }

    @Ignore
    public UsrBadges() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(int badgeID) {
        this.badgeID = badgeID;
    }
}