package com.example.stud_ie_app.DatabaseClasses;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.stud_ie_app.Badges;


@Database(entities = {Badges.class}, version = 1, exportSchema = false)
public abstract class BadgeDatabase extends RoomDatabase {
    public abstract BadgeDao mBadgeDao() ;
}

