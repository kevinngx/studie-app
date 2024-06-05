package com.example.stud_ie_app;

import android.app.Dialog;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.DatabaseClasses.ImageBank;

@Entity
public class Badges {

    @NonNull
    @PrimaryKey
    private int badgeID;
    private String name;
    private String description;
    private int icon;

    public Badges(int badgeID, String name, String description, int icon) {
        this.badgeID = badgeID;
        this.name = name;
        this.description = description;
        this.icon = icon;

    }

    public int getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(int badgeID) {
        this.badgeID = badgeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Badges{" +
                "badgeID=" + badgeID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon=" + icon +
                '}';
    }


}
