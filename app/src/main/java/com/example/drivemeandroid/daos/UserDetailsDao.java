package com.example.drivemeandroid.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drivemeandroid.models.UserDetails;

import java.util.List;

@Dao
public interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserDetails userDetails);

    @Query("SELECT * FROM user_details WHERE email = :email AND password = :password")
    UserDetails getUserByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM user_details WHERE userRole = :role")
    List<UserDetails> getDriversByRole(String role);

    @Query("SELECT * FROM user_details WHERE userId = :id")
    UserDetails getUser(int id);
}
