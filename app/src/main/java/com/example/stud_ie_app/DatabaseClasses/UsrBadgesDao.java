package com.example.stud_ie_app.DatabaseClasses;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.stud_ie_app.UsrBadges;

import java.util.List;

@Dao
public interface UsrBadgesDao {
    @Insert
    void insertSingleBadge(UsrBadges mUsrBadges);

    @Insert
    void insertMultipleBadges (List<UsrBadges> UsrBadgesList);

    @Query("SELECT * FROM UsrBadges WHERE badgeID = :badgeID ")
    UsrBadges fetchAllBadgesByID (int badgeID);

    @Query("SELECT * FROM UsrBadges WHERE username = :username  ORDER BY badgeID")
    List<UsrBadges> getAllBadgesByUser(String username);

}