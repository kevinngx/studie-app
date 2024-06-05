package com.example.stud_ie_app.DatabaseClasses;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.stud_ie_app.Users;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertOnlySingleUser (Users users);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleUsers (List<Users> usersList);

    @Query ("SELECT * FROM Users WHERE userName = :userName")
    Users fetchOneUserByUserName (String userName);

    @Query("SELECT * FROM Users ORDER BY score DESC")
    List<Users> getAll();

    @Query("UPDATE USERS SET score = score + :addNum WHERE userName = :userName")
    void updateScore (int addNum, String userName);

    @Query("UPDATE USERS SET password = :password WHERE userName = :userName")
    void updatePassword (String password, String userName);

    @Query("UPDATE USERS SET avatar = :avatar WHERE userName = :userName")
    void updateAvatar (int avatar, String userName);

    @Update
    void updateUser (Users users);

    @Delete
    void deleteUser (Users users);

    @Query("UPDATE USERS SET role = :newRole WHERE userName = :userName")
    void updateRole(String newRole, String userName);
}