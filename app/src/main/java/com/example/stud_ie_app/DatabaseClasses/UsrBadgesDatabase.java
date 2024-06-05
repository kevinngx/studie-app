package com.example.stud_ie_app.DatabaseClasses;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import com.example.stud_ie_app.UsrBadges;


@Database(entities = {UsrBadges.class}, version = 1, exportSchema = false)
public abstract class UsrBadgesDatabase extends RoomDatabase {
    public abstract UsrBadgesDao mUsrBadgesDao() ;
}

